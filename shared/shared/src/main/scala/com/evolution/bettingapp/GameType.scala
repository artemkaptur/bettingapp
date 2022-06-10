package com.evolution.bettingapp

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

case class GameType private(value: String) extends AnyVal

object GameType {
  implicit val decoder: Decoder[GameType] = deriveDecoder[GameType]
  implicit val encoder: Encoder[GameType] = deriveEncoder[GameType]
}
