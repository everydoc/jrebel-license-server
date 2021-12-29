package com.imjcker.ddns.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.toilelibre.libe.curl.Curl;

public interface DnsService {
    Logger log = LoggerFactory.getLogger(DnsService.class);


    default String getLocalIP() throws Exception {
        return Curl.$("curl http://ip.sb").trim().replace("\n", "");
    }

    void updateDns();
}
