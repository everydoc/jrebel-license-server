package com.imjcker.ddns.service.impl;

import com.imjcker.ddns.config.DnsConfigProperties;
import com.imjcker.ddns.service.MailService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 邮件服务实现
 *
 * @author THH (http://gitlab.imjcker.com/S000169)
 * @version 2021-09-17
 */
@Slf4j
@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;
    private final DnsConfigProperties dnsConfigProperties;
    private final MailProperties mailProperties;

    @SneakyThrows
    @Override
    public void sendSimpleTextMail(String to, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailProperties.getUsername());
        message.setSubject(dnsConfigProperties.getMail().getSubject());
        message.setTo(to);
        message.setText(content);

        javaMailSender.send(message);
    }
}
