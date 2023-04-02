package com.skewwhiffy.auraltester.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class EnumMappingConfiguration implements WebMvcConfigurer {
    @Override
    public void addFormatters(@NotNull FormatterRegistry registry) {
        ApplicationConversionService.configure(registry);
    }
}
