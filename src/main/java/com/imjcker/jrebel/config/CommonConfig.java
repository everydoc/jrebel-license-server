package com.imjcker.jrebel.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JrebelConfigurationProperties.class)
public class CommonConfig {
}
