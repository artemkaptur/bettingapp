package com.evolution.bettingapp

import cats.effect.Concurrent
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import org.http4s.circe.{accumulatingJsonOf, jsonEncoderOf}
import org.http4s.{EntityDecoder, EntityEncoder}

case class GameType private(value: String) extends AnyVal

object GameType {
  implicit val decoder: Decoder[GameType] = deriveDecoder[GameType]
  implicit val encoder: Encoder[GameType] = deriveEncoder[GameType]

  implicit def entityDecoder[F[_] : Concurrent]: EntityDecoder[F, GameType] = accumulatingJsonOf

  implicit def entityEncoder[F[_]]: EntityEncoder[F, GameType] = jsonEncoderOf
}
