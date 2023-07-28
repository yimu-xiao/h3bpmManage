package com.gstz.utils;

import org.apache.commons.codec.binary.Hex;

import java.util.Base64;

/**
 * @author yimu
 * @version 1.0
 * @description: 16进制转文本工具类
 * @date 2023/6/12 13:48
 */
public class Base64Utils {

    /**
     * base64编码格式的16进制转16进制字符串
     * @param base64HexString  base64编码格式的16进制
     * @return 16进制字符串
     */
    public static String base64ToHex(String base64HexString) {
        try {
            // 解码
            byte[] decode = Base64.getDecoder().decode(base64HexString);
            // 返回 16进制字符编码
            return Hex.encodeHexString(decode);
        } catch (RuntimeException e) {
            // 抛出自定义异常
            throw new RuntimeException("base64编码格式的16进制转16进制字符串错误，参数格式错误!");
        }
    }

    /**
     * 16进制字符串转Base64字符串
     * @param hexString
     * @return
     */
    public static String hexToBase64(String hexString) {
        try {
            // 16进制解码
            byte[] bytes = Hex.decodeHex(hexString);
            // 返回 编码成base64
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            // 抛出自定义异常
            throw new RuntimeException("16进制转base64编码错误, 参数格式错误!");
        }
    }
}