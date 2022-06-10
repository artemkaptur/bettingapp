package com.evolution.bettingapp.domain

import cats.effect.Concurrent
import com.evolution.bettingapp.Game
import org.http4s.circe.{accumulatingJsonOf, jsonEncoderOf}
import org.http4s.{EntityDecoder, EntityEncoder}

object GameEntityCodecs {
  implicit def entityDecoder[F[_] : Concurrent]: EntityDecoder[F, Game] = accumulatingJsonOf

  implicit def entityEncoder[F[_]]: EntityEncoder[F, Game] = jsonEncoderOf
}
