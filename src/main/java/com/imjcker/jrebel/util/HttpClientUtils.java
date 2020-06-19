package com.imjcker.jrebel.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

/**
 * <p>Title: HttpClientProxy.java
 * <p>Description: HttpClient请求代理
 * <p>Copyright: Copyright © 2017, CQzlll, All Rights Reserved.
 *
 * @author CQzlll.zl
 * @version 1.0
 */
public class HttpClientUtils {
    private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

    public static String postByJson(String uri, Map<String, String> headers, JSONObject jsonObject) {
        StringBuilder loggerMsg = new StringBuilder();
        loggerMsg.append("HttpClientProxy.postByJson(): 调用URI: ");
        loggerMsg.append(uri);
        loggerMsg.append(headers);
        loggerMsg.append(jsonObject);

        HttpPost httpPost = new HttpPost(uri);

        //构造参数数据实体
        if (jsonObject != null) {
            String transJson = jsonObject.toString();
            HttpEntity httpEntity = new StringEntity(transJson, ContentType.APPLICATION_JSON);
            httpPost.setEntity(httpEntity);
        }
        //构造请求头
        if (headers != null && !headers.isEmpty()) {
            Set<Map.Entry<String, String>> headerEntrySet = headers.entrySet();
            for (Map.Entry<String, String> headerEntry : headerEntrySet) {
                httpPost.setHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        String result = null;
        try {
            result = commonInvoke(httpPost);
            loggerMsg.append(result);
            log.info(loggerMsg.toString());
        } catch (IOException ex) {
            log.error(loggerMsg.toString(), ex);
        }
        return result;
    }

    public static String putByJson(String uri, Map<String, String> headers, JSONObject jsonObject) {
        HttpPut httpPut = new HttpPut(uri);
        //构造参数数据实体
        if (jsonObject != null) {
            String transJson = jsonObject.toString();
            HttpEntity httpEntity = new StringEntity(transJson, ContentType.APPLICATION_JSON);
            httpPut.setEntity(httpEntity);
        }
        //构造请求头
        if (headers != null && !headers.isEmpty()) {
            Set<Map.Entry<String, String>> headerEntrySet = headers.entrySet();
            for (Map.Entry<String, String> headerEntry : headerEntrySet) {
                httpPut.setHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        String result = null;
        try {
            result = commonInvoke(httpPut);
        } catch (IOException ex) {
            log.error("put by json error: ", ex);
        }
        return result;
    }

    public static String postBytes(String uri, Map<String, String> headers, byte[] bytes) {
        StringBuilder loggerMsg = new StringBuilder();
        loggerMsg.append("HttpClientProxy.postBytes(): 调用URI: ");
        loggerMsg.append(uri);
        loggerMsg.append(headers);
        HttpPost httpPost = new HttpPost(uri);
        ByteArrayEntity httpEntity = new ByteArrayEntity(bytes);
        httpPost.setEntity(httpEntity);

        //构造请求头
        if (headers != null && !headers.isEmpty()) {
            Set<Map.Entry<String, String>> headerEntrySet = headers.entrySet();
            for (Map.Entry<String, String> headerEntry : headerEntrySet) {
                httpPost.setHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }

        String result = null;

        try {
            result = commonInvoke(httpPost);
            loggerMsg.append(result);
            log.info(loggerMsg.toString());
        } catch (IOException ex) {
            log.error(loggerMsg.toString(), ex);
        }
        return result;
    }

    private static String commonInvoke(HttpEntityEnclosingRequestBase http) throws IOException {
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000 * 1000).setSocketTimeout(3000 * 1000).build();
        http.setConfig(requestConfig);

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;

        try {
            httpClient = HttpClients.createDefault();
            //调用
            httpResponse = httpClient.execute(http);
            HttpEntity httpEntity = httpResponse.getEntity();
            return EntityUtils.toString(httpEntity);
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException ex) {
                    log.error("关闭HttpResponse失败.", ex);
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException ex) {
                    log.error("关闭HttpClient失败.", ex);
                }
            }
        }
    }

    public static String get(String url, Map<String, String> headers, Map<String, String> params) {
        StringBuilder loggerMsg = new StringBuilder();
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (params != null) {
                for (String key : params.keySet()) {
                    builder.addParameter(key, params.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            //构造请求头
            if (headers != null && !headers.isEmpty()) {
                Set<Map.Entry<String, String>> headerEntrySet = headers.entrySet();
                for (Map.Entry<String, String> headerEntry : headerEntrySet) {
                    httpGet.setHeader(headerEntry.getKey(), headerEntry.getValue());
                }
            }
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ex) {
                    log.error("关闭HttpResponse失败.", ex);
                }
            }
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException ex) {
                    log.error("关闭HttpClient失败.", ex);
                }
            }
        }
        log.info(loggerMsg.toString());
        return resultString;
    }

    public static String get(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try {
            HttpGet get = new HttpGet(url);

            response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
                return result;
            }
        } catch (HttpHostConnectException e) {
            log.error("连接失败：{}, {}", e.getHost(), e.getMessage());
            return "{\"status\":\"999\"}";
        } catch (IOException e) {
            log.error("http client get error");
        } finally {
            release(httpClient, response);
        }
        return "";
    }

    public static String post(HttpPost httpPost) {
        RequestConfig requestConfig = RequestConfig
                .custom()
                .setSocketTimeout(30000)
                .setConnectTimeout(30000)
                .build();
        httpPost.setConfig(requestConfig);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                log.info("result: {}", result);
                return result;
            }
        } catch (IOException ex) {
            log.error("{}", ex.getMessage());
        } finally {
            release(httpClient, response);
        }

        return result;
    }

    public static void release(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        try {
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            log.error("资源释放报错：{}", e.getMessage());
        }
    }
}
