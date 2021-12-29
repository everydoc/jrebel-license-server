package com.imjcker.ddns.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "dns")
public class DnsConfigProperties {
    /**
     * 定时任务cron表达式
     */
    private String cron;

    /**
     * 获取本地IP的URL地址
     */
    private String findIpUrl;

    /**
     * 发送错误日志
     */
    private boolean sendErrorLog = false;

    /**
     * DNS变动通知邮箱
     */
    @NestedConfigurationProperty
    private MailConfig mail = new MailConfig();

    /**
     * 阿里云DNS配置
     */
    @NestedConfigurationProperty
    private AliDnsConfig ali = new AliDnsConfig();

    /**
     * Cloudflare DNS配置
     */
    @NestedConfigurationProperty
    private CloudflareDnsConfig cf = new CloudflareDnsConfig();

}
