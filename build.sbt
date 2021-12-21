val Http4sVersion = "0.21.31"
val CirceVersion = "0.13.0"
val MunitVersion = "0.7.27"
val LogbackVersion = "1.2.5"
val MunitCatsEffectVersion = "1.0.5"

Global / onChangedBuildSource := ReloadOnSourceChanges

lazy val bettingapp = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    organization := "com.evolution",
    name := "bettingapp",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.6",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s" %% "http4s-blaze-client" % Http4sVersion,
      "org.http4s" %% "http4s-circe" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "io.circe" %% "circe-generic" % CirceVersion,
      "org.scalameta" %% "munit" % MunitVersion % Test,
      "org.typelevel" %% "munit-cats-effect-2" % MunitCatsEffectVersion % Test,
      "ch.qos.logback" % "logback-classic" % LogbackVersion
    ))
  .aggregate(backend, frontend)

lazy val backend = (project in file("./backend"))
  .settings(
    name := "bettingapp-backend",
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.13.0" cross CrossVersion.full),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s" %% "http4s-blaze-client" % Http4sVersion,
      "org.http4s" %% "http4s-circe" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "io.circe" %% "circe-generic" % CirceVersion,
      "org.scalameta" %% "munit" % MunitVersion % Test,
      "org.typelevel" %% "munit-cats-effect-2" % MunitCatsEffectVersion % Test,
      "ch.qos.logback" % "logback-classic" % LogbackVersion
    )
  )

lazy val frontend = (project in file("./frontend"))
  .enablePlugins(
    ScalaJSBundlerPlugin,
    ScalaJSPlugin,
    ScalablyTypedConverterPlugin
  )
  .settings(
    stFlavour := Flavour.Slinky,
    webpackDevServerPort := 8024,
    version in webpack := "4.43.0",
    version in startWebpackDevServer := "3.11.0",
    webpackResources := baseDirectory.value / "webpack" * "*",
    webpackConfigFile in fastOptJS := Some(
      baseDirectory.value / "webpack" / "webpack-fastopt.config.js"
    ),
    webpackConfigFile in fullOptJS := Some(
      baseDirectory.value / "webpack" / "webpack-opt.config.js"
    ),
    webpackConfigFile in Test := Some(
      baseDirectory.value / "webpack" / "webpack-core.config.js"
    ),
    webpackDevServerExtraArgs in fastOptJS := Seq("--inline", "--hot", "--disableHostCheck"),
    webpackBundlingMode in fastOptJS := BundlingMode.LibraryOnly()
  ).configure(
  deps1,
  deps2,
  deps3,
)

lazy val deps1: Project => Project =
  _.settings(
    name := "bettingapp-frontend",
    scalaVersion := "2.13.6",
    scalacOptions += "-Ymacro-annotations",
    addCommandAlias("dev", ";set javaOptions  += \"-DIsLocal=true\";fastOptJS::startWebpackDevServer;~fastOptJS"),
    addCommandAlias("build", "fullOptJS::webpack"),
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "1.2.0",
      "me.shadaj" %%% "slinky-core" % "0.6.7",
      "me.shadaj" %%% "slinky-web" % "0.6.7",
      "me.shadaj" %%% "slinky-hot" % "0.6.7"
    ),
    Compile / npmDependencies ++= Seq(
      "react" -> "16.13.1",
      "react-dom" -> "16.13.1",
      "react-proxy" -> "1.1.8",
      "file-loader" -> "6.0.0",
      "style-loader" -> "1.2.1",
      "css-loader" -> "3.5.3",
      "html-webpack-plugin" -> "4.3.0",
      "copy-webpack-plugin" -> "5.1.1",
      "webpack-merge" -> "4.2.2")
  )

lazy val deps2: Project => Project =
  _.settings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "autowire" % "0.3.2",
      "io.suzaku" %%% "boopickle" % "1.3.2",
      "com.lihaoyi" %%% "upickle" % "1.2.2"
    )
  )

lazy val deps3: Project => Project =
  _.settings(
    stFlavour := Flavour.Slinky,
    useYarn := true,
    stIgnore := List("react-proxy", "copy-webpack-plugin", "css-loader", "file-loader", "style-loader", "webpack-merge"),
    Compile / npmDependencies ++= Seq(
      "@types/react" -> "16.9.42",
      "@types/react-dom" -> "16.9.8"
    )
  )
