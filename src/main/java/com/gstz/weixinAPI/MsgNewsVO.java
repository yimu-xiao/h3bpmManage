package com.gstz.weixinAPI;

import java.io.Serializable;

public class MsgNewsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6559905447957390983L;

	private String touser;
	private String toparty;
	private String totag;
	private String msgtype;
	private String agentid;
	private News news;
	
	
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


	public News getNews() {
		return news;
	}


	public void setNews(News news) {
		this.news = news;
	}



	
	
}
