package com.gstz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yimu
 * @version 1.0
 * @description: 企业微信登录
 * @date 2023/6/9 17:09
 */
public interface WxDHRService {
    String wxloginDHR(String code, HttpServletRequest request, HttpServletResponse response);
}
