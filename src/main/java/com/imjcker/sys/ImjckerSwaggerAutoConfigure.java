package com.imjcker.sys;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableConfigurationProperties({ImjckerSwaggerProperties.class, ImjckerSwaggerProperties.ApiInfo.class, ImjckerSwaggerProperties.Contact.class})
@ConditionalOnProperty(name = "imjcker.swagger.enabled", havingValue = "true")
public class ImjckerSwaggerAutoConfigure {

    @Autowired
    private ImjckerSwaggerProperties properties;

    @Bean
    public Docket docket() {
        ImjckerSwaggerProperties.ApiInfo apiInfo = properties.getApiInfo();
        ApiInfo apiInfo1 = new ApiInfoBuilder().build();
        BeanUtils.copyProperties(apiInfo, apiInfo1);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo1)
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }
}
