package com.gstz.utils;

import com.alibaba.fastjson.JSONObject;
import com.gstz.entity.request.InstanceIdResp;
import com.gstz.entity.request.user.UserResp;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yimu
 * @version 1.0
 * @description:
 * @date 2023/8/2 16:50
 */
public class DataInitUtil {
    static Logger logger = Logger.getLogger(DataInitUtil.class);

    static String token = getToken();

    public static String getToken()  {
        String url = "https://tworkflow.guoshou.com/api/bpm-api/accessToken";
        String systemCode = "TEST";
        String secret = "Test001";
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("systemCode", systemCode);
        param.put("secret", secret);
        String token = "";
        logger.info("token获取====》"+url);
        String data = HttpUtil.sendReq(url,param,null);
        JSONObject jsonObject = JSONObject.parseObject(data);
        logger.info("getToken_result："+jsonObject.toString());
        if("0".equals(jsonObject.get("code").toString())) {
            token = jsonObject.get("data").toString();
        }
        return token;
    }

    public static UserResp getUserInfo(String name)  {
        String url = "https://tworkflow.guoshou.com/api/bpm-api/getUserInfoByName?name="+name;
        String data = HttpUtil.sendReq(url,null,token);
        UserResp userResp = JSONObject.parseObject(data, UserResp.class);
        if (userResp == null || userResp.getCode() != 0) {
            data = HttpUtil.sendReq(url,null,getToken());
            userResp = JSONObject.parseObject(data, UserResp.class);
        }
        logger.info("getUserInfo_result:"+userResp.toString());
        return userResp;
    }


    public static InstanceIdResp initInstance(Map param)  {
        String url = "https://tworkflow.guoshou.com/api/bpm-api/submitInstance";
        String data = HttpUtil.sendReq(url,param,token);
        InstanceIdResp instanceIdResp = JSONObject.parseObject(data, InstanceIdResp.class);
        if (instanceIdResp == null || instanceIdResp.getCode() != 0) {
            data = HttpUtil.sendReq(url,null,getToken());
            instanceIdResp = JSONObject.parseObject(data, InstanceIdResp.class);
        }
        logger.info("initInstance_result:"+instanceIdResp.toString());
        return instanceIdResp;
    }

}
