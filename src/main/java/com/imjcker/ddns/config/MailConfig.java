package com.imjcker.ddns.config;

import lombok.Data;

/**
 * 邮件配置
 *
 * @author THH (http://gitlab.imjcker.com/S000169)
 * @version 2021-09-17
 */
@Data
public class MailConfig {
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 收件人邮箱
     */
    private String user;
}
