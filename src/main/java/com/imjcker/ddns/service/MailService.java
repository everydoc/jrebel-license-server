package com.imjcker.ddns.service;

/**
 * 邮件服务接口
 *
 * @author THH (http://gitlab.imjcker.com/S000169)
 * @version 2021-09-17
 */
public interface MailService {
    void sendSimpleTextMail(String to, String content);
}
