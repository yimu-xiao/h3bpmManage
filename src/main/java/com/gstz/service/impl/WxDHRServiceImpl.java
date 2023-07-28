package com.gstz.service.impl;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.gstz.entity.UserInfo;
import com.gstz.service.UserInfoService;
import com.gstz.service.WxDHRService;
import com.gstz.utils.HttpUtil;
import com.gstz.utils.Sm4Util;
import com.gstz.weixinAPI.WXBaseAPI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static com.gstz.utils.Base64Utils.hexToBase64;

/**
 * @author yimu
 * @version 1.0
 * @description:
 * @date 2023/6/9 17:11
 */
@Service
public class WxDHRServiceImpl implements WxDHRService {

    Logger logger = Logger.getLogger(getClass());

    @Value("${wx.corpid}")
    String corpid;

    @Value("${wx.agentid}")
    String agentId;

    @Value("${wx.corpsecret}")
    String corpsecret;

    @Value("${SM4.key}")
    String key;

    @Value("${SM4.iv}")
    String iv;

    @Value("${dhr.url}")
    String dhrUrl;
    @Value("${dhr.appName}")
    String appName;

    @Resource
    WXBaseAPI wxBaseAPI;

    @Resource
    UserInfoService userInfoService;

    @Override
    public String wxloginDHR(String code, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String userId = "";
        String spa = "";
        try{
            userId = session.getAttribute("userId").toString();
            spa = session.getAttribute("spa").toString();
        }catch (Exception e){
            logger.info("首次进入。。");
        }
        logger.info("session中：userId==》"+userId+"  spa==》"+spa);
        if ("".equals(userId) || userId == null ) {
            // 【1】企业微信验证身份
            Map<String, String> datamap = wxBaseAPI.getQyWxAccessUserInfoUp(code, corpsecret, corpid);
            logger.info("企业微信返回信息:" + new Gson().toJson(datamap));
            if (null == datamap || null == datamap.get("UserId") || !("0").equals(datamap.get("errcode"))) {
                logger.error("企业微信身份验证失败！返回数据为空！");
                return "error";
            }
            userId = datamap.get("UserId");
            session.setAttribute("userId", userId);
        }

        if ( "".equals(spa) || spa == null) {

            // 【2】 查询用户工号
            logger.info("企业微信编号为：" + userId);
            UserInfo userInfo = userInfoService.selectByWecharid(userId);
            if (userInfo != null) {
                spa = userInfo.getEmployeecode();
            } else {
                logger.error("用户中心查询不到该用户！请核对用户信息");
                return "error";
            }
            logger.info("用户工号为：" + spa);
            if (spa == null || "".equals(spa)) {
                logger.error("工号为空！");
                return "error";
                //TODO 测试，--》指向肖昌一
//            spa = "60000361";
            }
            session.setAttribute("spa", spa);
        }

        // 【3】 包装加密
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("workno", spa);
        jsonObject.put("source", appName); //appName
        jsonObject.put("timestamp",System.currentTimeMillis());
        logger.info("加密前参数为："+jsonObject.toJSONString());
        String encode = hexToBase64(Sm4Util.encode(Mode.ECB, Padding.PKCS5Padding, key, iv, jsonObject.toJSONString()));
        logger.info("encode："+ encode);

        String location = HttpUtil.httpDHRGet(dhrUrl, encode);
        logger.info("相应信息："+location);
        return  "redirect:"+location;
    }
}
