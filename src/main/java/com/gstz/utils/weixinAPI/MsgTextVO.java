package com.gstz.utils.weixinAPI;

import java.io.Serializable;

public class MsgTextVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2862779593326131205L;

	
	private String touser;
	private String toparty;
	private String totag;
	private String msgtype;
	private String agentid;
	private MsgTextContentVO text;
	private String safe = "0";
	public String getSafe() {
		return safe;
	}
	public void setSafe(String safe) {
		this.safe = safe;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getToparty() {
		return toparty;
	}
	public void setToparty(String toparty) {
		this.toparty = toparty;
	}
	public String getTotag() {
		return totag;
	}
	public void setTotag(String totag) {
		this.totag = totag;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	public MsgTextContentVO getText() {
		return text;
	}
	public void setText(MsgTextContentVO text) {
		this.text = text;
	}
	
	
}
