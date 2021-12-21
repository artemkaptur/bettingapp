package com.evolution.bettingapp

import cats.effect.Sync
import cats.implicits._
import com.evolution.bettingapp.domain.User
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

object BettingappRoutes {

  def loginRoutes[F[_] : Sync]: HttpRoutes[F] = {
    import io.circe.generic.auto._
    import org.http4s.circe.CirceEntityCodec._

    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] {
      case req@POST -> Root / "login" =>
        req.as[User].flatMap(_ => Ok("Login is successful"))

      case GET -> Root / "kek" =>
        Ok("blablabla")
    }
  }
}