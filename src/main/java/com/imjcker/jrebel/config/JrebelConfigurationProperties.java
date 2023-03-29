package com.imjcker.jrebel.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 自定义配置
 */
@ConfigurationProperties(prefix = "jrebel")
public class JrebelConfigurationProperties {

    /**
     * 获取jrebel部署服务器的公网IP地址
     */
    @Deprecated
    private String ipFetchUrl;
    /**
     * jrebel active addr for China
     */
    private String urlCn;
    /**
     * jrebel active addr for global
     */
    private String urlGlobal;
    /**
     * social networks url list
     */
    private List<Social> social;

    @Deprecated
    public String getIpFetchUrl() {
        return ipFetchUrl;
    }

    @Deprecated
    public void setIpFetchUrl(String ipFetchUrl) {
        this.ipFetchUrl = ipFetchUrl;
    }

    public String getUrlCn() {
        return urlCn;
    }

    public void setUrlCn(String urlCn) {
        this.urlCn = urlCn;
    }

    public String getUrlGlobal() {
        return urlGlobal;
    }

    public void setUrlGlobal(String urlGlobal) {
        this.urlGlobal = urlGlobal;
    }

    public List<Social> getSocial() {
        return social;
    }

    public void setSocial(List<Social> social) {
        this.social = social;
    }

    public static class Social{
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
