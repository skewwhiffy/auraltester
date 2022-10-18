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
