package com.evolution.bettingapp.domain

import cats.effect.Concurrent
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.circe.{accumulatingJsonOf, jsonEncoderOf}
import org.http4s.{EntityDecoder, EntityEncoder}

case class Game(id: Int, gameType: GameType, team1: String, team2: String)

object Game {
  implicit val decoder: Decoder[Game] = deriveDecoder[Game]
  implicit val encoder: Encoder[Game] = deriveEncoder[Game]

  implicit def entityDecoder[F[_] : Concurrent]: EntityDecoder[F, Game] = accumulatingJsonOf

  implicit def entityEncoder[F[_]]: EntityEncoder[F, Game] = jsonEncoderOf
}
