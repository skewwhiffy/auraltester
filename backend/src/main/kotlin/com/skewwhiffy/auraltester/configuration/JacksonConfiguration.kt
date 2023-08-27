package com.skewwhiffy.auraltester.configuration

import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.boot.convert.ApplicationConversionService

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class JacksonConfiguration : WebMvcConfigurer {
    @Bean
    fun kotlin() = kotlinModule()

    override fun addFormatters(registry: FormatterRegistry) = ApplicationConversionService.configure(registry)
}