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

libraryDependencies += "org.junit.jupiter" % "junit-jupiter" % "5.9.1" % Test
libraryDependencies += "org.assertj" % "assertj-core" % "3.23.1" % Test
libraryDependencies += "net.aichler" % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test
libraryDependencies += "org.springframework.boot" % "spring-boot-starter-web" % springBootVersion
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion
libraryDependencies += "com.fasterxml.jackson.module" % "jackson-module-scala_2.13" % jacksonVersion