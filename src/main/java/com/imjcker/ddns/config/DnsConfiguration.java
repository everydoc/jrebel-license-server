package com.imjcker.ddns.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@AllArgsConstructor
public class DnsConfiguration {
    private final DnsConfigProperties dnsConfigProperties;

    @Bean
    @ConditionalOnProperty(prefix = "dns.ali", name = "enable", havingValue = "true")
    public IAcsClient iAcsClient() {
        IClientProfile profile = DefaultProfile.getProfile(dnsConfigProperties.getAli().getRegion(), dnsConfigProperties.getAli().getAccessKey(), dnsConfigProperties.getAli().getAccessSecret());
        // 若报Can not find endpoint to access异常，请添加以下此行代码
        // DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Alidns", "alidns.aliyuncs.com");
        return new DefaultAcsClient(profile);
    }
}
