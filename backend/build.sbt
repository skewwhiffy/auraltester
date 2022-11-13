name := """auraltester"""
organization := "com.skewwhiffy"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  guice,
  "org.mockito" %% "mockito-scala-scalatest" % "1.17.12" % Test,
  "org.scalatest" %% "scalatest" % "3.2.14" % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
)
PlayKeys.devSettings := Seq("play.server.http.port" -> "3001")
