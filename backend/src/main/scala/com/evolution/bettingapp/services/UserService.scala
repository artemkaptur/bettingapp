package com.evolution.bettingapp.services

import cats.effect.Async
import cats.implicits._
import com.evolution.bettingapp.domain.Creds
import com.evolution.bettingapp.repository.UserRepositoryT
import com.evolution.bettingapp.util.JWToken
import com.evolution.bettingapp.util.JwtUtil.jwtEncode

trait UserServiceT[F[_]]{
  def userLogin(creds: Creds): F[Either[String, JWToken]]
}

class UserService[F[_]: Async](repo: UserRepositoryT[F]) extends UserServiceT[F]{

  override def userLogin(creds: Creds): F[Either[String, JWToken]] = {
    for {
      user <- repo.userByLogin(creds.login)
    } yield user match {
      case Some(user) if user.password == creds.password  =>
        jwtEncode(user).asRight[String]
      case None                               =>
        s"Error. User is not registered".asLeft[JWToken]
      case _                                  =>
        s"Error. Wrong password".asLeft[JWToken]
    }
  }

}
