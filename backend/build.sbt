lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "3.2.0"
    )),
    name := "example"
  )

libraryDependencies += "org.junit.jupiter" % "junit-jupiter" % "5.9.1" % Test
libraryDependencies += "org.assertj" % "assertj-core" % "3.23.1" % Test
libraryDependencies += "net.aichler" % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test
