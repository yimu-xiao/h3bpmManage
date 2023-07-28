package com.gstz.weixinAPI;

import java.io.Serializable;

public class JsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4800384344665794671L;

	
	private String timestamp;
	private String nonceStr;
	private String signature;
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
}
