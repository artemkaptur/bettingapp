package com.evolution.bettingapp

import cats.effect.{ConcurrentEffect, Timer}
import com.evolution.bettingapp.routes.BettingappRoutes
import doobie.hikari.HikariTransactor
import fs2.Stream
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware.Logger

import scala.concurrent.ExecutionContext.global

object BettingappServer {

  def stream[F[_] : ConcurrentEffect](implicit T: Timer[F], xa: HikariTransactor[F]): Stream[F, Nothing] = {
    for {
      httpApp <- Stream(BettingappRoutes.loginRoutes[F].orNotFound)
      finalHttpApp = Logger.httpApp(true, true)(httpApp)
      exitCode <- BlazeServerBuilder[F](global)
        .bindHttp(8883, "0.0.0.0")
        .withHttpApp(finalHttpApp)
        .serve
    } yield exitCode
  }.drain
}
