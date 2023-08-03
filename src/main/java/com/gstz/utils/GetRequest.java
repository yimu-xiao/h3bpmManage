package com.gstz.utils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * GET请求类
 */
public class GetRequest {
    private static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    public String getData(String url)  {
        BufferedReader in = null;
        HttpURLConnection conn;
        String result = "";
        try {
        	System.out.println("开始。。");
            trustAllHttpsCertificates();
            HostnameVerifier hv = (urlHostName, session) -> true;
            System.out.println("请求。。1");
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
            conn = (HttpURLConnection)new URL(url).openConnection();
            System.out.println("请求。。2");
            // 发送GET请求必须设置如下两行
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            InputStream inputStream = conn.getInputStream();
            int read = inputStream.read();
            // flush输出流的缓冲
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            System.out.println("请求。。3");
            while ((line = in.readLine()) != null) {
            	System.out.println(line);
                result += line;
            }
            System.out.println("请求。。4");
        } catch (Exception e) {
            System.out.println("发送 GET 请求出现异常！\t请求ID:"+url+"\n"+e.getMessage()+"\n");
            System.out.println(url+"\n"+e.getMessage()+"\n");
            e.printStackTrace();
        } finally {// 使用finally块来关闭输出流、输入流
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            	System.out.println("关闭数据流出错了！\n"+ex.getMessage()+"\n");
            }
        }
        // 获得相应结果result,可以直接处理......
        System.out.println("最终返回result = " + result);
        return result;
    }
    static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }
}