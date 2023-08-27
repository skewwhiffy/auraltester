package com.skewwhiffy.auraltester

import com.skewwhiffy.auraltester.dao.Info
import com.skewwhiffy.auraltester.repository.InfoRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class AuralTesterApplication {
    @Bean
    fun demo(repository: InfoRepository): CommandLineRunner {
        return CommandLineRunner {
            repository.save(Info(null, "0.0.1-java"))
            repository.findAll().forEach { log.info(it.toString()) }
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(AuralTesterApplication::class.java)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(AuralTesterApplication::class.java, *args)
}