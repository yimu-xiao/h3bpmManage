package com.gstz.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class StringUtil {
    /**
     * 判断字符串是否为null
     */
    public static boolean isEmpty(String string) {
        return string == null || "".equals(string.trim())
                || "null".equalsIgnoreCase(string.trim());
    }

    /**
     * 判断Json格式
     */
    public static String jsonFormat(String str) {
        if (!str.startsWith("{")) {
            str = "{" +str;
        }
        if (!str.endsWith("}")) {
            str = str + "{" ;
        }
        return str;
    }

    public static String changeChina(String url) {
        char[] tp = url.toCharArray();
        String now = "";
        for (char ch : tp) {
            if (ch >= 0x4E00 && ch <= 0x9FA5) {
                try {
                    now += URLEncoder.encode(ch + "", "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                now += ch;
            }
        }
        return now.replaceAll(" ","%20");
    }

}
