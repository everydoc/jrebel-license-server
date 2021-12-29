package com.imjcker.ddns.service.impl;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeSubDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeSubDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordRequest;
import com.imjcker.ddns.config.DnsConfigProperties;
import com.imjcker.ddns.service.DnsService;
import com.imjcker.ddns.service.MailService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.toilelibre.libe.curl.Curl;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
@AllArgsConstructor
@ConditionalOnProperty(prefix = "dns.ali", name = "enable", havingValue = "true")
public class AliDnsServiceImpl implements DnsService {
    private final IAcsClient client;
    private final DnsConfigProperties dnsConfig;
    private final MailService mailService;

    @SneakyThrows
    private List<DescribeSubDomainRecordsResponse.Record> getDnsList() {
        DescribeSubDomainRecordsRequest domain = dnsConfig.getAli().getRecord();
        DescribeSubDomainRecordsResponse response = client.getAcsResponse(domain);

        return response.getDomainRecords();
    }

    @Override
    public String getLocalIP() throws Exception {
//        String localIp = Curl.$("curl " + dnsConfig.getFindIpUrl()).trim().replace("\n", "");
        InputStream content = Curl.curl("curl " + dnsConfig.getFindIpUrl()).getEntity().getContent();
        String ip = IOUtils.toString(content, StandardCharsets.UTF_8.name()).trim().replace("\n", "");
        log.info(ip);
        String pattern = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(ip);
        if (m.matches()) {
            return ip;
        }else {
            throw new Exception("获取本地IP地址失败。");
        }
    }

    @Override
    @Scheduled(cron = "${dns.cron}")
    public void updateDns() {
        try {
            String localIP = getLocalIP();
            List<DescribeSubDomainRecordsResponse.Record> records = getDnsList();
            String dnsIP = records.get(0).getValue();
            if (!StringUtils.equals(localIP, dnsIP)) {
                for (DescribeSubDomainRecordsResponse.Record record : records) {
                    UpdateDomainRecordRequest request = new UpdateDomainRecordRequest();
                    request.setRecordId(record.getRecordId());
                    request.setRR(record.getRR());
                    request.setType(record.getType());
                    request.setValue(localIP);
                    client.getAcsResponse(request);
                }
                String dnsIPNew = getDnsList().get(0).getValue();
                log.info("本地IP为:{},远程IP为:{},开始修改远程IP，更新后的远程IP为:{}", localIP, dnsIP, dnsIPNew);
                DescribeSubDomainRecordsRequest domain = dnsConfig.getAli().getRecord();
                mailService.sendSimpleTextMail(dnsConfig.getMail().getUser(), "域名: " + domain.getSubDomain() + "的DNS记录变更如下:\n" + dnsIP + " -> " + dnsIPNew + "\n");
            }
        } catch (Exception e) {
            if (dnsConfig.isSendErrorLog()) {
                log.error("发送系统异常日志。");
                StringBuilder errMsg = new StringBuilder("错误日志：").append("\n");
                for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                    errMsg.append(stackTraceElement.toString()).append("\n");
                }
                mailService.sendSimpleTextMail(dnsConfig.getMail().getUser(), errMsg.toString());
                log.info("邮件发送完毕。");
            }
        }
    }
}
