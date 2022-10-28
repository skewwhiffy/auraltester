package com.skewwhiffy.auraltester

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.{Bean, Profile}

object Application {
  def main(args: Array[String]): Unit = SpringApplication.run(classOf[Application])
}

@SpringBootApplication
class Application {
}
