val springBootVersion = "2.6.7"
val jacksonVersion = "2.13.2"

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.skewwhiffy",
      scalaVersion := "2.13.10"
    )),
    name := "Aural Tester"
  )

libraryDependencies ++= Seq(
  "org.springframework.boot" % "spring-boot-starter-web" % springBootVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.13" % jacksonVersion,
  "org.mockito" %% "mockito-scala-scalatest" % "1.17.12" % Test,
  "org.scalatest" %% "scalatest" % "3.2.14" % Test
)