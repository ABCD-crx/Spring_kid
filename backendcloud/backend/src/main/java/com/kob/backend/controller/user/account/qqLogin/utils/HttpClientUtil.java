package com.kob.backend.controller.user.account.qqLogin.utils;

/**
 * Author: chen
 * Time: 2024/2/16 22:19
 */
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

// 工具类  发送一个http的请求
// 传入一个url 和参数 向url发送请求 将返回结果变成字符串

public class HttpClientUtil {
    public static String get(String url, List<NameValuePair> params) {
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
        uriBuilder.setParameters(params);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            CloseableHttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException | URISyntaxException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}