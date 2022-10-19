package com.skewwhiffy.auraltester

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.{Bean, Profile}


@SpringBootApplication
class Application

@main
def main(): Unit = SpringApplication.run(classOf[Application])
