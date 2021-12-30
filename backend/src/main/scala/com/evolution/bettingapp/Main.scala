package com.evolution.bettingapp

import cats.effect.{ExitCode, IO, IOApp}
import com.evolution.bettingapp.db.{DbTransactor, FlywayMigrator}

object Main extends IOApp {

  def run(args: List[String]) = {
    FlywayMigrator.initializeAndMigrate()
    DbTransactor.pooled[IO].use { implicit xa =>
      BettingappServer.stream[IO].compile.drain.as(ExitCode.Success)
    }
  }
}
