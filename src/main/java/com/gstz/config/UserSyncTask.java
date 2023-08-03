package com.gstz.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gstz.entity.UserInfo;
import com.gstz.entity.request.sso.SSOTokenResp;
import com.gstz.service.UserInfoService;
import com.gstz.entity.request.GetRequest;
import com.gstz.utils.ApplicationContextUtil;
import com.gstz.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;

/**
 * @author yimu
 * @version 1.0
 * @description:
 * @date 2022/12/6 17:18
 */
@Component
public class UserSyncTask {
    private final Logger logger = LoggerFactory.getLogger(UserSyncTask.class);

    @Value("${sso.appcode}")
    private String appcode;
    @Value("${sso.appsecret}")
    private String appsecret;
    @Value("${sso.getToken}")
    private String getTokenUrl;
    @Value("${sso.getUsers}")
    private String getUsersUrl;

    @Scheduled(cron = "${cron.userSyncTask}")   //一天一次
//    @Scheduled(cron = "0/5 * * * * *") //每5秒一次
    public void userSyncTask(){
        logger.info("用户信息同步开始==》");
        String url = getTokenUrl + appcode + "&appsecret=" + appsecret;
        UserInfoService userInfoService = (UserInfoService) ApplicationContextUtil.getBean("userInfoService");
        JSONObject jsonObject = getJsonObject(getUsersUrl,"user",url,"all");
        JSONArray userInfoList = jsonObject.getJSONArray("userInfoList");
        for (int i = 0; i < userInfoList.size(); i++) {
            JSONObject userList = userInfoList.getJSONObject(i);
            UserInfo userInfo = JSON.toJavaObject(userList, UserInfo.class);
            userInfo.setOrders(userInfo.getOrder());
            userInfo.setOrder(null);
            try{
                userInfoService.saveOrUpdate(userInfo);
            }catch (Exception e){
                logger.info("userInfo插入失败："+ userInfo.getUid()+" "+userInfo.getName());
            }
        }
        logger.info("用户信息同步结束==》");
    }

    public JSONObject getJsonObject(String urlResp, String type, String urlToken,String sort) {
        HashMap<String, String> tokenMap = getToken(urlToken);
        if (Long.parseLong(tokenMap.get("etime")) < System.currentTimeMillis()) {
            tokenMap = getToken(urlToken);
        }
        String token = tokenMap.get("token");
        logger.info("------------->token："+token);
        String url;
        UserInfoService userInfoService = (UserInfoService) ApplicationContextUtil.getBean("userInfoService");

        if ((type.equals("user") && userInfoService.count() == 0)) {
            url = urlResp + token;
        }else{
            if ("all".equals(sort)) {
                url = urlResp + token;
            }else{
                url = urlResp + token + "&time=" + LocalDate.now();
            }
        }
        String resp = getURLResponseStr(url);
//        System.out.println("------------->请求结果："+resp);

        JSONObject jsonObject = JSONObject.parseObject(StringUtil.jsonFormat(resp));
        //获取部门列表成功
        for (int i = 0; i < 3; i++) {
            if (!"0".equals(jsonObject.get("code"))) {
                resp = getURLResponseStr(url);
                jsonObject = JSONObject.parseObject(StringUtil.jsonFormat(resp));
            } else {
                break;  //请求成功则跳出循环
            }
        }
        return jsonObject;
    }

    //获取Token
    private static HashMap<String, String> getToken(String url) {
        String tokenResp = getURLResponseStr(url);
        JSONObject jsonObject = JSONObject.parseObject(StringUtil.jsonFormat(tokenResp));
        SSOTokenResp resp = JSON.toJavaObject(jsonObject, SSOTokenResp.class);
        String token;
        String expires;
        HashMap<String, String> map = new HashMap<>();
        if (resp.getCode() == 0) { //获取token成功
            token = resp.getAccess_token();
            expires = resp.getExpires_in();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + Integer.parseInt(expires)/60/60);
            long timeInMillis = calendar.getTimeInMillis();
            map.put("token", token);
            map.put("stime", ""+System.currentTimeMillis());
            map.put("etime", ""+timeInMillis);
        }
        return map;
    }

    private static String getURLResponseStr(String url){
        GetRequest g = new GetRequest();
        return g.getData(url);
    }

}

