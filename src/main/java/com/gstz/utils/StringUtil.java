package com.gstz.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static List<String> cutParams(String url){
        ArrayList<String> list = new ArrayList<>();
        // 定义正则表达式
        Pattern pattern = Pattern.compile("\\{(.*?)\\}");

        // 创建 Matcher 对象，并传入待匹配的字符串
        Matcher matcher = pattern.matcher(url);

        // 循环匹配并提取值
        while (matcher.find()) {
            // 获取匹配到的值，不包括 `{}` 符号
            list.add(matcher.group(1));
        }
        return list;
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
