val springBootVersion = "2.6.7"
val jacksonVersion = "2.13.2"

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.skewwhiffy",
      scalaVersion := "2.13.10"
"org.mockito" % "mockito-junit-jupiter" % "4.8.1" % Test,
    )),
    name := "Aural Tester"
  )

libraryDependencies ++= Seq(
  "org.springframework.boot" % "spring-boot-starter-web" % springBootVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.13" % jacksonVersion,
  "org.assertj" % "assertj-core" % "3.23.1" % Test,
  "net.aichler" % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test,
  "org.scalamock" %% "scalamock" % "5.2.0" % Test,
  "org.scalatest" %% "scalatest" % "3.2.14" % Test,
)