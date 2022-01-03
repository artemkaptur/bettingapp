package com.evolution.bettingapp

import cats.effect.Concurrent
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.circe.{accumulatingJsonOf, jsonEncoderOf}
import org.http4s.{EntityDecoder, EntityEncoder}

case class Game(id: Int, gameType: GameType, team1: String, team2: String, score1: Int, score2: Int, coefficient1: Double, coefficient2: Double)

object Game {
  implicit val decoder: Decoder[Game] = deriveDecoder[Game]
  implicit val encoder: Encoder[Game] = deriveEncoder[Game]

  implicit def entityDecoder[F[_] : Concurrent]: EntityDecoder[F, Game] = accumulatingJsonOf

  implicit def entityEncoder[F[_]]: EntityEncoder[F, Game] = jsonEncoderOf
}
