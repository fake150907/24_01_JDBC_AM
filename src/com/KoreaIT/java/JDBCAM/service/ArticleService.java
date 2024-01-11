package com.KoreaIT.java.JDBCAM.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.JDBCAM.dao.ArticleDao;
import com.KoreaIT.java.JDBCAM.dto.Article;

public class ArticleService {
	private ArticleDao acd;

	public ArticleService(Connection conn) {
		acd = new ArticleDao(conn);
	}

	public int doWrite(String title, String body) {
		int id = acd.doWrite(title, body);
		return id;
	}

	public void doModify(int id, String newTitle, String newBody) {
		acd.doModify(id, newTitle, newBody);
	}

	public void doRemove(int id) {
		acd.doRemove(id);
	}

	public List<Article> showList() {
		List<Article> articles = acd.showList();
		return articles;
	}

	public Map<String, Object> foundArticleMap(int id) {
		Map<String, Object> aritlceMap = acd.foundArticleMap(id);
		return aritlceMap;
	}
}
