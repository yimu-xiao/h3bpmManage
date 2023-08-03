package com.gstz.utils.weixinAPI;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.gstz.utils.CheckUtil;
import com.gstz.utils.HttpUtil;
import com.gstz.utils.RedisUtils;
import com.gstz.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class WXBaseAPI {
	Logger logger = Logger.getLogger(getClass());

	/*
	 * 验证消息是否来自于服务器(加密非首次验证)
	 */
	public boolean isFromWeixin(String signature, String token, String timestamp, String nonce) {
		if (StringUtil.isEmpty(timestamp) || StringUtil.isEmpty(signature)
				|| StringUtil.isEmpty(nonce))
			return false;
		return CheckUtil.checkSignature(signature, timestamp, nonce, token);
	}

	/*
	 * 获取access_token
	 */
	public String get_access_token(String corpid, String corpsecret) {
		Jedis jedis = null;
		boolean redisBrokenflag = false;
		String t_accessToken = null;
		String grant_type = "client_credential";// 获取access_token填写client_credential
		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret;
		logger.info("开始请求Access_token,url=" + url);
		try {
			jedis = RedisUtils.getJedis();
			//Access_token = RedisUtils.getJedis().get(corpsecret);//每个月，都是这里出问题
			if(jedis!=null) {
				t_accessToken = jedis.get(corpsecret);
				logger.info("从redis中获得access_token："+t_accessToken);
			}
			if (null == t_accessToken) {
				String r = HttpUtil.httpGet(url);
				if (r != null && !r.equals("")) {
					JSONObject jobject = JSONObject.parseObject(r);
					for (String key : jobject.keySet()) {
						logger.info(key + " : " + jobject.getString(key));
					}
					t_accessToken = jobject.getString("access_token");
					jedis.setex(corpsecret, 7200, t_accessToken);
					logger.info("从weixin获得access_token："+t_accessToken);
				}
			}
		} catch (Exception e) {
			redisBrokenflag = RedisUtils.handleJedisException(e);
			RedisUtils.returnResource(jedis, redisBrokenflag);
			logger.info("获取access_token报错！"+e.getMessage());
			logger.info(e.getStackTrace());
		}
		logger.info("返回Access_token为： " + t_accessToken);
		return t_accessToken;
	}

	public Map<String, String> getQyWxAccessUserInfoUp(String code, String corpsecret, String corpid) {

		// String code = request.getParameter("code");
		String access_token = get_access_token(corpid, corpsecret);// 获取Access_token
		if (null == code || null == access_token || ("").equals(access_token)) {
			return null;
		}

		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=" + access_token + "&code="
				+ code;
		Map<String, String> info = new HashMap<String, String>();
		// logger.info("开始请求网页用户简要信息:" + url);
		String r = HttpUtil.httpGet(url);
		if (r != null && !r.equals("")) {
			JSONObject jobject = JSONObject.parseObject(r);
			// 返回的信息：errcode，errmsg，UserId，DeviceId，user_ticket，expires_in
			for (String key : jobject.keySet()) {
				logger.info(key + " : " + jobject.getString(key));
				info.put(key, jobject.getString(key));
			}
		}
		return info;
	}

	public JsVO getSignature(String corpid, String corpsecret, String url) {
		JsVO jsVO = new JsVO();

		// 获取access_token
		String access_token = get_access_token(corpid, corpsecret);// 获取Access_token
		if (null == access_token || ("").equals(access_token)) {
			return null;
		}
		// 获取jsapi_ticket
		String jsapi_ticket = "";
		String jsapi_ticket_url = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=" + access_token;
		String jsapi_ticket_res = HttpUtil.httpGet(jsapi_ticket_url);
		if (null != jsapi_ticket_res && !("").equals(jsapi_ticket_res)) {
			JSONObject jobject = JSONObject.parseObject(jsapi_ticket_res);
			if (!"0".equals(jobject.getString("errcode"))) {
				return null;
			}
			jsapi_ticket = jobject.getString("ticket");
		}
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		String nonceStr = UUID.randomUUID().toString();
		jsVO.setTimestamp(timestamp);
		jsVO.setNonceStr(nonceStr);
		String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url="
				+ url;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			String signature = byteToHex(crypt.digest());
			jsVO.setSignature(signature);
		} catch (Exception e) {
			return null;
		}
		return jsVO;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	/**
	 * @param corpid
	 * @param corpsecret
	 *            "接收消息的应用secret"
	 * @param agentId
	 *            "接收消息的应用id"
	 * @param toUserIDList
	 *            "useriD|userID2"
	 * @param text
	 *            "消息内容，最长不超过2048个字节"
	 * @return
	 */
	public String sendMsgText(String corpid, String corpsecret, String agentId, String toUserIDList,
			MsgTextContentVO text) {
		String access_token = get_access_token(corpid, corpsecret);// 获取Access_token
		if (null == access_token || ("").equals(access_token)) {
			return null;
		}

		String sendUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + access_token;
		MsgTextVO params = new MsgTextVO();
		params.setTouser(toUserIDList);
		params.setMsgtype("text");
		params.setAgentid(agentId);
		params.setText(text);
		Gson gson = new Gson();
		System.out.println("sendMsgTxt:" + gson.toJson(params));
		String sendMsgText_res = HttpUtil.sendPost(sendUrl, gson.toJson(params));
		System.out.println("返回结果：" + sendMsgText_res);

		return "";
	}

	/**
	 * @param corpid
	 * @param corpsecret
	 *            "接收消息的应用secret"
	 * @param agentId
	 *            "接收消息的应用id"
	 * @param toUserIDList
	 *            "useriD|userID2"
	 * @param
	 *            "消息内容，最长不超过2048个字节"
	 * @return
	 */
	public String sendMsgNews(String corpid, String corpsecret, String agentId, String toUserIDList, News news) {
		String access_token = get_access_token(corpid, corpsecret);// 获取Access_token
		if (null == access_token || ("").equals(access_token)) {
			return null;
		}

		String sendUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + access_token;
		MsgNewsVO params = new MsgNewsVO();
		params.setTouser(toUserIDList);
		params.setMsgtype("news");
		params.setAgentid(agentId);
		params.setNews(news);
		Gson gson = new Gson();
		System.out.println("sendNews:" + gson.toJson(params));
		String sendMsgText_res = HttpUtil.sendPost(sendUrl, gson.toJson(params));
		System.out.println("返回结果：" + sendMsgText_res);

		return "";
	}
}
