package com.imjcker.ddns.service.impl;

import com.imjcker.ddns.service.DnsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
@ConditionalOnProperty(prefix = "dns.cf", name = "enable", havingValue = "true")
public class CfDnsServiceImpl implements DnsService {
    @Override
    public void updateDns() {
        log.info("Cloudflare dns update process");
    }
}
