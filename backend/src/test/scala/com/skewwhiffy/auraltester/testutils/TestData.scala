package com.skewwhiffy.auraltester.testutils

import java.util.UUID
import scala.util.Random

object TestData {
  object random {
    private lazy val random = new Random()

    def string: String = s"${UUID.randomUUID()}"

    def oneOf[T](source: T*): T = source(random.nextInt(source.size))
  }
}
