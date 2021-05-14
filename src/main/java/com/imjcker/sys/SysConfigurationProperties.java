package com.imjcker.sys;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("imjcker.oshi")
public class SysConfigurationProperties {
    private SysViewServlet sysViewServlet;


    /**
     * servlet 配置
     */
    @Data
    public static class SysViewServlet{
        private boolean enabled = true;
        private String urlPattern = "/sys/status";
    }

}
