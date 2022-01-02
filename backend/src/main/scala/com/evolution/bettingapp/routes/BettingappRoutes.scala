package com.evolution.bettingapp.routes

import cats.effect.Async
import cats.implicits.{toFlatMapOps, toFunctorOps}
import com.evolution.bettingapp.domain.{Creds, GameType}
import com.evolution.bettingapp.repository.{GameRepository, UserRepository}
import com.evolution.bettingapp.services.{GameService, UserService}
import doobie.hikari.HikariTransactor
import org.http4s.dsl.Http4sDsl
import org.http4s.headers.Authorization
import org.http4s._

object BettingappRoutes {

  def bettingappRoutes[F[_] : Async](implicit xa: HikariTransactor[F]): HttpRoutes[F] = {
    import io.circe.generic.auto._
    import org.http4s.circe.CirceEntityCodec._

    val userRepo = new UserRepository[F]
    val gamesRepo = new GameRepository[F]
    val userService = new UserService[F](userRepo)
    val gameService = new GameService[F](gamesRepo)
    val dsl = new Http4sDsl[F] {}
    import dsl._

    implicit val gameQueryParamDecoder: QueryParamDecoder[GameType] =
      QueryParamDecoder[String].map(GameType.apply)
    object GameQueryParamMatcher extends QueryParamDecoderMatcher[GameType]("gameType")

    HttpRoutes.of[F] {
      case req@POST -> Root / "login" =>
        req.as[Creds].flatMap(creds => {
          for {
            res <- userService.userLogin(creds)
            resp <- res match {
              case Left(s)      => BadRequest(s)
              case Right(token) => Ok(s"Login successful. Authorization token: ${token.value}",
                Authorization(Credentials.Token(AuthScheme.Bearer, token.value)))
            }
          } yield resp
        }.map(_.putHeaders(Header("Access-Control-Allow-Origin", "*"))))

      case GET -> Root / "games" :? GameQueryParamMatcher(gameType) => {
        for {
          res <- gameService.getGames(gameType)
          resp <- res match {
            case Left(s)      => BadRequest(s)
            case Right(games) => Ok(games)
          }
        } yield resp
      }.map(_.putHeaders(Header("Access-Control-Allow-Origin", "*")))
    }
  }
}
