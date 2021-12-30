package com.evolution.bettingapp.db

import org.flywaydb.core.Flyway

object FlywayMigrator {

  def initializeAndMigrate() = {
    val flyway = Flyway.configure()
      .dataSource(
        "jdbc:postgresql://0.0.0.0:5432/bettingapp",
        "postgres",
        "8888")
      .locations("classpath:/db/migration")
      .baselineOnMigrate(true)
      .load()
    flyway.repair()
    flyway.migrate()
  }
}
