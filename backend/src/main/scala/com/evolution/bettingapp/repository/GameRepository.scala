package com.evolution.bettingapp.repository

import cats.effect.Async
import com.evolution.bettingapp.{Game, GameType}
import doobie.hikari.HikariTransactor
import doobie.implicits._

trait GameRepositoryT[F[_]] {
  def gamesByType(gameType: GameType): F[List[Game]]
}

class GameRepository[F[_] : Async](implicit xa: HikariTransactor[F]) extends GameRepositoryT[F] {

  def gamesByType(gameType: GameType): F[List[Game]] = {
    sql"""SELECT id, game_type, team1, team2, score1, score2, coefficient1, coefficient2 FROM games WHERE game_type = ${gameType.value}
       """
      .query[Game]
      .to[List]
      .transact(xa)
  }
}
