package com.imjcker.ddns.config;

import com.aliyuncs.alidns.model.v20150109.DescribeSubDomainRecordsRequest;
import lombok.Data;

/**
 * @author thh
 * 阿里云DNS配置
 */
@Data
public class AliDnsConfig {
    /**
     * 动态解析域名
     */
    private DescribeSubDomainRecordsRequest record = new DescribeSubDomainRecordsRequest();
    /**
     * 开启
     */
    private boolean enable;
    /**
     * 地区
     */
    private String region = "cn-hangzhou";
    /**
     * 阿里云的accessKey
     */
    private String accessKey;
    /**
     * 阿里云的accessSecret
     */
    private String accessSecret;
}
