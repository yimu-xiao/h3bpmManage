package com.gstz.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.*;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yimu
 * @version 1.0
 * @description:
 * @date 2023/8/2 16:50
 */
public class dataInitController {
    Logger logger = Logger.getLogger(getClass());

    public String getToken() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String url = "https://tworkflow.guoshou.com/api/bpm-api/accessToken";
        String api = "";
        String systemCode = "MAS-6-00-H201904";
        String secret = "4e293f2c6418e1a4c83033b7604a67f3";
        url = url + api;
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("systemCode", systemCode);
        param.put("secret", secret);
        String token = "";
        System.out.println(url);
        String data = sendReq(url,param,null);
        JSONObject jsonObject = JSONObject.parseObject(data);
        logger.info("getToken_result:"+jsonObject.toString());
        if("0".equals(jsonObject.get("code").toString())) {
            token = jsonObject.get("data").toString();
        }
        return token;
    }

    private String sendReq(String url, Map<String,Object> map, String token) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        // 创建自定义的SSLContext
        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial((chain, authType) -> true) // 忽略所有证书验证错误
                .build();

        // 创建自定义的SSL连接工厂
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);



        String respContent = "";
        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000) // 连接超时时间
                    .setSocketTimeout(5000) // 读取超时时间
                    .build();

            // 创建自定义的HttpClient
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslSocketFactory).setDefaultRequestConfig(requestConfig)
                    .build();

            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(JSONObject.toJSONString(map), "UTF-8"));
            httpPost.setHeader("Accept", "*/*");
            httpPost.setHeader("Content-type", "application/json");

            httpPost.setConfig(requestConfig);

            if(token!=null) {
                httpPost.setHeader("accessToken",token);
            }

            httpclient.execute(httpPost);
            HttpResponse resp = httpclient.execute(httpPost);
            if (resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respContent;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String str = "";
//        String s = HttpUtil.httpGet("http://10.5.12.70:8080/api/bpm-api/accessToken");
        dataInitController dataInitController = new dataInitController();
        String token = dataInitController.getToken();
        System.out.println(token);

//        String s = HttpUtil.sendPost("https://tworkflow.guoshou.com/api/bpm-api/accessToken", "{\"systemCode\":\"Test\", \"secret\":\"Test001\"}");
//        System.out.println(s);
    }

}
