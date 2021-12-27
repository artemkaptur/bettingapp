package com.evolution.bettingapp

import cats.effect.Sync
import cats.implicits._
import com.evolution.bettingapp.domain.User
import org.http4s.{Header, HttpRoutes}
import org.http4s.dsl.Http4sDsl

object BettingappRoutes {

  def loginRoutes[F[_] : Sync]: HttpRoutes[F] = {
    import io.circe.generic.auto._
    import org.http4s.circe.CirceEntityCodec._

    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] {
      case req@POST -> Root / "login" =>
        req.as[User].flatMap(_ => Ok("Login is successful")).map(_.putHeaders(Header("Access-Control-Allow-Origin", "*")))

      case GET -> Root / "kek" =>
        Ok("blablabla").map(_.putHeaders(Header("Access-Control-Allow-Origin", "*")))
    }
  }
}