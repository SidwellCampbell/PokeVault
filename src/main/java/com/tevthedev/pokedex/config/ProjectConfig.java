package com.tevthedev.pokedex.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.tevthedev.pokedex.proxy")
public class ProjectConfig {
}

