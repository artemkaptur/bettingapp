package com.evolution.bettingapp.repository

import cats.effect.Async
import com.evolution.bettingapp.domain.User
import doobie.hikari.HikariTransactor
import doobie.implicits._

trait UserRepositoryT[F[_]]{
  def userByLogin(login: String): F[Option[User]]
}

class UserRepository[F[_]: Async](implicit xa: HikariTransactor[F]) extends UserRepositoryT[F] {

  def userByLogin(login: String): F[Option[User]] = {
    sql"""SELECT login, password, name, is_admin FROM users WHERE login = $login
       """
      .query[User]
      .option
      .transact(xa)
  }
}
