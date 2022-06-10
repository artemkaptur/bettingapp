package com.evolution.bettingapp.services

import cats.effect.Async
import cats.implicits._
import com.evolution.bettingapp.repository.GameRepositoryT
import com.evolution.bettingapp.{Game, GameType}

trait GameServiceT[F[_]] {
  def getGames(gameType: GameType): F[Either[String, List[Game]]]
}

class GameService[F[_] : Async](repo: GameRepositoryT[F]) extends GameServiceT[F] {

  override def getGames(gameType: GameType): F[Either[String, List[Game]]] = {
    for {
      games <- repo.gamesByType(gameType)
    } yield games match {
      case Nil => s"Error. User is not registered".asLeft[List[Game]]
      case _   => games.asRight[String]
    }
  }
}
