package com.skewwhiffy.auraltester.configuration

import com.fasterxml.jackson.module.kotlin.kotlinModule

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class JacksonConfiguration {
    @Bean
    open fun kotlin() = kotlinModule()
}