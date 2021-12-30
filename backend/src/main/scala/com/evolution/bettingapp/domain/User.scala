package com.evolution.bettingapp.domain

import cats.effect.Concurrent
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import org.http4s.circe.{accumulatingJsonOf, jsonEncoderOf}
import org.http4s.{EntityDecoder, EntityEncoder}

case class User(login: String, password: String, name: String, isAdmin: Boolean)

object User {
  implicit val decoder: Decoder[User] = deriveDecoder[User]
  implicit val encoder: Encoder[User] = deriveEncoder[User]

  implicit def entityDecoder[F[_] : Concurrent]: EntityDecoder[F, User] = accumulatingJsonOf

  implicit def entityEncoder[F[_]]: EntityEncoder[F, User] = jsonEncoderOf
}
