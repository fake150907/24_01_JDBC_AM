package com.KoreaIT.java.JDBCAM.service;

import java.sql.Connection;
<<<<<<< HEAD
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.JDBCAM.Article;
import com.KoreaIT.java.JDBCAM.dao.ArticleDao;

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

	public Map<String, Object> getArticleMap(int id) {
		Map<String, Object> aritlceMap = acd.getArticleMap(id);
		return aritlceMap;
	}
=======
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.KoreaIT.java.JDBCAM.util.DBUtil;
import com.KoreaIT.java.JDBCAM.util.SecSql;

public class ArticleService {
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public ArticleService() {
	}

	public int write(Connection conn, String title, String body) {
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW(),");
		sql.append("updateDate = NOW(),");
		sql.append("title = ?,", title);
		sql.append("`body`= ?;", body);
		

		int id = DBUtil.insert(conn, sql);
		
		return id;
	}

	public boolean modify(Connection conn, int articleId, String newTitle, String newBody) {
		try {
			String sql = "UPDATE article ";
			sql += "SET updateDate = NOW() ";
			if (newTitle.length() > 0) {
				sql += ",title = '" + newTitle + "'";
			}
			if (newTitle.length() > 0) {
				sql += ",`body` = '" + newBody + "'";
			}
			sql += " WHERE id = " + articleId;

			pstmt = conn.prepareStatement(sql);

			int r = pstmt.executeUpdate(sql);

			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean remove(Connection conn, int articleId) {
		try {

			String sql = "DELETE FROM article ";
			sql += " WHERE id = " + articleId + ";";

			pstmt = conn.prepareStatement(sql);

			int r = pstmt.executeUpdate(sql);

			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			closeRs();
			closePstmt();
		}
		return false;
	}

	private void closePstmt() {
		try {
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void closeRs() {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

>>>>>>> 7db270959f17c47689049647e1234489900231f0
}
