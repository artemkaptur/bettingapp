package com.evolution.bettingapp

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class Blabla(id: Int)

object Blabla {
  implicit val decoder: Decoder[Blabla] = deriveDecoder[Blabla]
  implicit val encoder: Encoder[Blabla] = deriveEncoder[Blabla]
}
