package com.imjcker.sys;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("imjcker.swagger")
public class ImjckerSwaggerProperties {
    private boolean enabled;

    private ApiInfo apiInfo;
    private String basePackage;
//    private Predicate<String> predicate;

    @Data
    @ConfigurationProperties("imjcker.swagger.api-info")
    public static class ApiInfo {
        private String title = "接口文档";
        private String description = "文档描述";
        private String termsOfServiceUrl = "服务条款";
        private Contact contact;
        private String version = "1.0";
        private String license;
        private String licenseUrl;

        public springfox.documentation.service.ApiInfo buildSwaggerApiInfo() {
            springfox.documentation.service.Contact contact = new springfox.documentation.service.Contact(this.contact.name, this.contact.url, this.contact.email);
            return new springfox.documentation.service.ApiInfo(this.title, this.description, this.version, this.termsOfServiceUrl, contact, this.license, this.licenseUrl);
        }

        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
        }
    }

    @Data
    @ConfigurationProperties("imjcker.swagger.api-info.contact")
    public static class Contact {
        private String name = "imjcker";
        private String url = "http://imjcker.com";
        private String email = "helloalanturing@icloud.com";

        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
        }
    }


}
