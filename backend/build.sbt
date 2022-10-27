val springBootVersion = "2.6.7"
val jacksonVersion = "2.13.2"

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.skewwhiffy",
      scalaVersion := "3.2.0"
    )),
    name := "Aural Tester"
  )

libraryDependencies ++= Seq(
  "org.junit.jupiter" % "junit-jupiter" % "5.9.1" % Test,
  "org.assertj" % "assertj-core" % "3.23.1" % Test,
  "net.aichler" % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test,
  "org.mockito" % "mockito-core" % "4.8.1" % Test,
  "org.mockito" % "mockito-junit-jupiter" % "4.8.1" % Test,
  "org.springframework.boot" % "spring-boot-starter-web" % springBootVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.13" % jacksonVersion
)