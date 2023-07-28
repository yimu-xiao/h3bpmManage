package com.gstz.weixinAPI;

import java.io.Serializable;
import java.util.List;

public class News implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7539529737849233938L;

	private List<NewsArticle> articles;

	public List<NewsArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<NewsArticle> articles) {
		this.articles = articles;
	}
}