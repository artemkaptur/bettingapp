package com.evolution.bettingapp.db

import cats.effect.{Async, Blocker, ContextShift, Resource}
import com.evolution.bettingapp.db.DbConfig.{dbDriverName, dbPwd, dbUrl, dbUser}
import doobie.hikari.HikariTransactor
import doobie.util.ExecutionContexts

object DbTransactor {

  def pooled[F[_] : ContextShift : Async]: Resource[F, HikariTransactor[F]] =
    for {
      ce <- ExecutionContexts.fixedThreadPool[F](10)
      be <- Blocker[F]
      xa <- HikariTransactor.newHikariTransactor[F](
        driverClassName = dbDriverName,
        url = dbUrl,
        user = dbUser,
        pass = dbPwd,
        connectEC = ce, // await connection on this EC
        blocker = be, // execute JDBC operations on this EC
      )
    } yield xa
}
