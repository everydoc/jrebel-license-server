package com.imjcker.ddns.api;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.*;
import com.aliyuncs.exceptions.ClientException;
import com.imjcker.ddns.common.Api;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.toilelibre.libe.curl.Curl;

@Slf4j
@RestController
@RequestMapping("dns")
@AllArgsConstructor
public class AliDnsController {
    private final IAcsClient client;

    @GetMapping("getDomains")
    public Object getDomains() throws ClientException {
        DescribeDomainsRequest request = new DescribeDomainsRequest();
        DescribeDomainsResponse response = client.getAcsResponse(request);
        return Api.success(response.getDomains());
    }

    @RequestMapping(value = "getSubDomains", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getSubDomains(DescribeSubDomainRecordsRequest request) throws ClientException {
        DescribeSubDomainRecordsResponse response = client.getAcsResponse(request);
        return Api.success(response);
    }

    /**
     * 获取解析记录
     * @return dns records
     */
    @RequestMapping(value = "getRecords", method = {RequestMethod.GET, RequestMethod.POST})
    @SneakyThrows
    public Object getRecords(@RequestBody DescribeDomainRecordsRequest request) {
        DescribeDomainRecordsResponse response = client.getAcsResponse(request);
        return Api.success(response.getDomainRecords());
    }

    /**
     * 获取解析记录
     * @return dns records
     */
    @RequestMapping(value = "getRecordInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @SneakyThrows
    public Object getRecordInfo(@RequestBody DescribeDomainRecordInfoRequest request) {
        DescribeDomainRecordInfoResponse response = client.getAcsResponse(request);
        return Api.success(response);
    }

    /**
     * 更新解析记录
     * @return dns records
     */
    @RequestMapping(value = "updateRecordInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @SneakyThrows
    public Object updateRecordInfo(@RequestBody UpdateDomainRecordRequest request) {
        UpdateDomainRecordResponse response = client.getAcsResponse(request);
        return Api.success(response);
    }

    /**
     * 更新解析记录
     * @return dns records
     */
    @RequestMapping(value = "getLocalIP", method = {RequestMethod.GET, RequestMethod.POST})
    @SneakyThrows
    public Object getLocalIP() {
        String s = Curl.$("curl http://ip.sb");
        return Api.success(s);
    }
}
