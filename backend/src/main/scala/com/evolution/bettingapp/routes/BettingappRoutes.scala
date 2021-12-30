package com.evolution.bettingapp.routes

import cats.effect.Async
import cats.implicits.{toFlatMapOps, toFunctorOps}
import com.evolution.bettingapp.domain.Creds
import com.evolution.bettingapp.repository.UserRepository
import com.evolution.bettingapp.services.UserService
import doobie.hikari.HikariTransactor
import org.http4s.dsl.Http4sDsl
import org.http4s.headers.Authorization
import org.http4s.{AuthScheme, Credentials, Header, HttpRoutes}

object BettingappRoutes {

  def loginRoutes[F[_] : Async](implicit xa: HikariTransactor[F]): HttpRoutes[F] = {
    import io.circe.generic.auto._
    import org.http4s.circe.CirceEntityCodec._

    val repo = new UserRepository[F]
    val userService = new UserService[F](repo)
    val dsl = new Http4sDsl[F] {}
    import dsl._
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

      case GET -> Root / "kek" =>
        Ok("blablabla").map(_.putHeaders(Header("Access-Control-Allow-Origin", "*")))
    }
  }
}
