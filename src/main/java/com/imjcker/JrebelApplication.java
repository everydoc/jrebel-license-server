package com.imjcker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

//@Slf4j
@SpringBootApplication
public class JrebelApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(JrebelApplication.class);
    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(JrebelApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("License Server started at http://localhost:" + port);
        log.info("JetBrains Activation address is: http://localhost:" + port + "/");
        log.info("JRebel 7.1 and earlier version Activation address is: http://localhost:" + port + "/{tokenname}, with any email.");
        log.info("JRebel 2018.1 and later version Activation address is: http://localhost:" + port + "/{guid}(eg:http://localhost:" + port + "/" + UUID.randomUUID().toString() + "), with any email.");
    }
}
