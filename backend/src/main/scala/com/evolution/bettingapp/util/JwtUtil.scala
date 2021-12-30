package com.evolution.bettingapp.util

import cats.implicits.toBifunctorOps
import com.evolution.bettingapp.domain.User
import io.circe.syntax.EncoderOps
import pdi.jwt.{JwtAlgorithm, JwtCirce, JwtClaim}

object JwtUtil {

  private val key = "secretKey"
  private val algo = JwtAlgorithm.HS256

  def jwtEncode(user: User): JWToken = {
    val userJson = user.asJson.noSpaces
    val claim = JwtClaim(content = userJson)
    JWToken(JwtCirce.encode(claim, key, algo))
  }

  def tokenDecode(token: JWToken): Either[String, JwtClaim] = {
    JwtCirce.decode(token.value, key, Seq(algo)).toEither.leftMap(_ => "Error decoding jwt claim from token. The token might be not valid.")
  }
}
