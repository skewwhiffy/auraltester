package com.skewwhiffy.auraltester

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.{CorsRegistry, WebMvcConfigurer}

@Configuration
class WebConfiguration extends WebMvcConfigurer {
  override def addCorsMappings(registry: CorsRegistry): Unit = {
    registry.addMapping("/**").allowedMethods("*")
  }
}
