package com.gstz.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.SM4;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import static com.gstz.utils.Base64Utils.hexToBase64;

/**
 * @author yimu
 * @version 1.0
 * @description: 请求头参数加密
 * 为保证参数安全，因此对 UKEY 参数进行加密，参数加密采用 SM4 算法加密，加密模式为 ECB 模式，其中加密使用的 Key 和偏移量如下：
 * 测试环境 SM4 暂定配置：
 * Key：EHRApp9876543210
 * 偏移量：ChinaLifeAPP01
 * @date 2023/6/12 10:20
 */
public class Sm4Util {

    static Logger logger = Logger.getLogger(Sm4Util.class);
    /**
     * sm4加密
     * @param mode sm4加密的模式 ECB,CBC
     * @param padding 加密填充方式
     * @param key 加密秘钥 转成byte[] 大小16
     * @param iv 偏移量，CBC模式下需要传入,否则系统随机生成iv每次加密结果都不一样 转成byte[] 大小16
     * @param content 加密内容
     * @return
     */
    public static String encode(Mode mode, Padding padding, String key, String iv, String content) {
        try {
            //校验参数
            if(StringUtil.isEmpty(content)) {
                return null;
            }
            SymmetricCrypto sm4 = new SM4(mode, padding, key.getBytes());
            String encryptHex = null;
            if(mode.equals(Mode.CBC)) {
                sm4 = new SM4(mode, padding, key.getBytes(),iv.getBytes());
            }
            // 在NoPadding模式下需要手动补齐分组16字节倍数
            if(padding.equals(Padding.NoPadding)) {
                byte[] data = padding(content);
                encryptHex = sm4.encryptHex(data);
            }
            else {
                encryptHex = sm4.encryptHex(content.getBytes());
            }
            return encryptHex;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MS4加密失败");
        }
        return null;
    }
    /**
     * sm4解密
     * @param mode sm4加密的模式 ECB,CBC
     * @param padding 加密填充方式
     * @param key 加密秘钥 转成byte[] 大小16
     * @param iv 偏移量，CBC模式下需要传入,否则系统随机生成iv每次加密结果都不一样 转成byte[] 大小16
     * @param encodeContent 需解密字符串
     * @return
     */
    public static String decode(Mode mode, Padding padding,String key, String iv,String encodeContent) {
        try {
            //校验参数
            if(StringUtil.isEmpty(encodeContent)) {
                return null;
            }
            SymmetricCrypto sm4 = new SM4(mode, padding, key.getBytes());
            if(mode.equals(Mode.CBC)) {
                sm4 = new SM4(mode, padding, key.getBytes(),iv.getBytes());
            }
            String decryptStr = sm4.decryptStr(encodeContent, CharsetUtil.CHARSET_UTF_8);
            return decryptStr;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("SM4解密失败");
        }
        return null;
    }

    // 在NoPadding模式下需要手动对齐16字节的倍数
    public static byte[] padding(String arg_text) {
        byte[] encrypt = arg_text.getBytes();

        if (encrypt.length % 16 != 0) {
            byte[] padded = new byte[encrypt.length + 16 - (encrypt.length % 16)];
            System.arraycopy(encrypt, 0, padded, 0, encrypt.length);
            encrypt = padded;
        }
        return encrypt;
    }

    public static void main(String[] args) {
//        key: Invoice987654321
//        iv: ChinaLifeOA789
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("workno", "60000361");
        jsonObject.put("source", "GSTZEHR"); //appName
        jsonObject.put("timestamp",System.currentTimeMillis());
        logger.info("加密前参数为："+jsonObject.toJSONString());
        String encode = encode(Mode.ECB, Padding.PKCS5Padding, "Invoice987654321", "ChinaLifeOA789", jsonObject.toJSONString());
        logger.info(hexToBase64(encode));
    }

}
