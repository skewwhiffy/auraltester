package com.skewwhiffy.auraltester

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

object Application {
  def main(args: Array[String]): Unit = SpringApplication.run(classOf[Application])
}

@SpringBootApplication
class Application {
}
