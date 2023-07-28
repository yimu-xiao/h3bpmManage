package com.gstz.controller;

import com.gstz.service.WxDHRService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理企业微信端的登录
 * @author yimu
 */
@Controller
@RequestMapping("/gstzDHR")
public class WXLoginController {
	Logger logger = Logger.getLogger(getClass());

	@Resource
	WxDHRService wxDHRService;
	/**
	 * 企业微信登录入口
	 * @return
	 */
	@RequestMapping("/wxlogin")
	public String wxlogin(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) {
		String r = "error";
		try {
			r = wxDHRService.wxloginDHR(code,request, response);
		}catch (Exception e) {
			logger.info(e.getMessage());
		}
		logger.info("/wxlogin 返回："+ r);
		return r;
	}

	/**
	 * 企业微信登录入口
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
