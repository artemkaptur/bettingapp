package com.evolution.bettingapp.domain

import cats.effect.Concurrent
import com.evolution.bettingapp.GameType
import org.http4s.circe.{accumulatingJsonOf, jsonEncoderOf}
import org.http4s.{EntityDecoder, EntityEncoder}

object GameTypeEntityCodecs {

  implicit def entityDecoder[F[_] : Concurrent]: EntityDecoder[F, GameType] = accumulatingJsonOf

  implicit def entityEncoder[F[_]]: EntityEncoder[F, GameType] = jsonEncoderOf
}
