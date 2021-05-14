package com.imjcker.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SysConfigurationProperties.class)
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "imjcker.oshi.sys-view-servlet.enabled", havingValue = "true")
public class SysAutoConfigure {
    @Autowired
    private SysConfigurationProperties configurationProperties;

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new SysViewServlet(), configurationProperties.getSysViewServlet().getUrlPattern());
    }
}

