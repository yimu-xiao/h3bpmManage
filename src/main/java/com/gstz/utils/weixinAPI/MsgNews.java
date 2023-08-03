package com.gstz.utils.weixinAPI;

import java.io.Serializable;
import java.util.List;

public class MsgNews implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7539529737849233938L;

	private List<MsgNewsArticleVO> articles;

	public List<MsgNewsArticleVO> getArticles() {
		return articles;
	}

	public void setArticles(List<MsgNewsArticleVO> articles) {
		this.articles = articles;
	}
}