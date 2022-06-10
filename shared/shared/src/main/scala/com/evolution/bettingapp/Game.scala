package com.evolution.bettingapp

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

case class Game(id: Int, gameType: GameType, team1: String, team2: String, score1: Int, score2: Int, coefficient1: Double, coefficient2: Double)

object Game {
  implicit val decoder: Decoder[Game] = deriveDecoder[Game]
  implicit val encoder: Encoder[Game] = deriveEncoder[Game]
}
