package com.KoreaIT.java.JDBCAM.controller;

import java.sql.Connection;
<<<<<<< HEAD
=======
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
>>>>>>> 7db270959f17c47689049647e1234489900231f0
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.KoreaIT.java.JDBCAM.Article;
import com.KoreaIT.java.JDBCAM.service.ArticleService;
<<<<<<< HEAD
import com.KoreaIT.java.JDBCAM.util.Util;

public class ArticleController {
	private Scanner sc;
	private String cmd;
	private ArticleService asv;

	public ArticleController(String cmd, Connection conn) {
		sc = new Scanner(System.in);
		this.cmd = cmd;
		asv = new ArticleService(conn);
	}

	public void doWrite() {
=======
import com.KoreaIT.java.JDBCAM.util.DBUtil;
import com.KoreaIT.java.JDBCAM.util.SecSql;

public class ArticleController {
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private int articleId;
	private Scanner sc;
	private String cmd;
	private String[] cmdDiv;
	private ArticleService asv;

	public ArticleController(String cmd) {
		articleId = 0;
		sc = new Scanner(System.in);
		this.cmd = cmd;
		cmdDiv = cmd.split(" ");
		asv = new ArticleService();
	}

	public void write(Connection conn) {
>>>>>>> 7db270959f17c47689049647e1234489900231f0
		System.out.println("==글쓰기==");
		System.out.print("제목 : ");
		String title = sc.nextLine();
		System.out.print("내용 : ");
		String body = sc.nextLine();

		if (title.length() < 1 || body.length() < 1) {
			System.out.println("올바른 내용을 입력해주세요.");
			return;
		}

<<<<<<< HEAD
		int id = asv.doWrite(title, body);
=======
		int id = asv.write(conn, title, body);
>>>>>>> 7db270959f17c47689049647e1234489900231f0

		System.out.println(id + "번 글이 생성되었습니다");
	}

<<<<<<< HEAD
	public void showList() {
		System.out.println("==목록==");

		List<Article> articles = asv.showList();
=======
	public void list(Connection conn) {
		System.out.println("==목록==");

		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("ORDER BY id DESC;");

		List<Map<String, Object>> articleListMap = DBUtil.selectRows(conn, sql);

		for (Map<String, Object> articleMap : articleListMap) {
			articles.add(new Article(articleMap));
		}
>>>>>>> 7db270959f17c47689049647e1234489900231f0

		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return;
		}

		System.out.println("  번호  /   제목  ");
<<<<<<< HEAD

		for (Article article : articles) {
			System.out.printf("  %d     /   %s   \n", article.getId(), article.getTitle());
		}
	}

	public void showDetail() {

		int id = 0;

		try {
			id = Integer.parseInt(cmd.split(" ")[2]);
		} catch (Exception e) {
			System.out.println("번호는 정수로 입력해");
			return;
		}

		System.out.println("==상세보기==");

		Map<String, Object> articleMap = asv.getArticleMap(id);

		if (articleMap.isEmpty()) {
			System.out.println(id + "번 글은 없습니다.");
			return;
		}

		Article article = new Article(articleMap);

		System.out.println("번호 : " + article.getId());
		System.out.println("작성날짜 : " + Util.getNowDate_TimeStr(article.getRegDate()));
		System.out.println("수정날짜 : " + Util.getNowDate_TimeStr(article.getUpdateDate()));
		System.out.println("제목 : " + article.getTitle());
		System.out.println("내용 : " + article.getBody());

	}

	public void doModify() {
		int id = 0;

		try {
			id = Integer.parseInt(cmd.split(" ")[2]);
		} catch (Exception e) {
			System.out.println("번호는 정수로 입력해");
			return;
		}

		Map<String, Object> articleMap = asv.getArticleMap(id);

		if (articleMap.isEmpty()) {
			System.out.println(id + "번 글은 없습니다.");
			return;
		}

		System.out.println("==수정==");
		System.out.print("새 제목 : ");
		String title = sc.nextLine().trim();
		System.out.print("새 내용 : ");
		String body = sc.nextLine().trim();

		asv.doModify(id, title, body);

		System.out.println(id + "번 글이 수정되었습니다.");
	}

	public void doRemove() {
		int id = 0;

		try {
			id = Integer.parseInt(cmd.split(" ")[2]);
		} catch (Exception e) {
			System.out.println("번호는 정수로 입력해");
			return;
		}

		Map<String, Object> articleMap = asv.getArticleMap(id);

		if (articleMap.isEmpty()) {
			System.out.println(id + "번 글은 없습니다.");
			return;
		}

		asv.doRemove(id);

		System.out.println(id + "번 글이 삭제되었습니다.");
=======
		for (Article article : articles) {
			System.out.printf("  %d     /   %s   \n", article.getId(), article.getTitle());
		}

	}

	public void detail(Connection conn) {
		if (cmdDiv.length < 3) {
			System.out.println("명령어를 똑바로 입력해라 인간.");
			return;
		}

		try {
			articleId = Integer.parseInt(cmdDiv[2]);
		} catch (NumberFormatException e) {
			System.out.println("정수를 똑바로 입력해라 인간.");
			return;
		}

		try {
			String sql = "SELECT * ";
			sql += " FROM article";
			sql += " WHERE id = " + articleId + ";";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery(sql);
			System.out.printf("== %s ==\n", cmd);

			while (rs.next()) {
				System.out.println("id : " + rs.getInt("id"));
				System.out.println("regDate : " + rs.getString("regDate"));
				System.out.println("updateDate : " + rs.getString("updateDate"));
				System.out.println("title : " + rs.getString("title"));
				System.out.println("body : " + rs.getString("body"));
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

	}

	public void modify(Connection conn) {
		if (cmdDiv.length < 3) {
			System.out.println("명령어를 똑바로 입력해라 인간.");
			return;
		}
		try {
			articleId = Integer.parseInt(cmdDiv[2]);
		} catch (NumberFormatException e) {
			System.out.println("정수를 똑바로 입력해라 인간.");
			return;
		}
		String newTitle = "";
		String newBody = "";

		System.out.print("(수정)제목 : ");
		newTitle = sc.nextLine().trim();
		System.out.print("(수정)내용 : ");
		newBody = sc.nextLine().trim();

		boolean status = asv.modify(conn, articleId, newTitle, newBody);

		if (status == false) {
			System.out.println(articleId + "번 게시글은 없습니다.");
			return;
		}

		System.out.println(articleId + "번 게시글이 수정되었습니다.");

	}

	public void remove(Connection conn) {
		if (cmdDiv.length < 3) {
			System.out.println("명령어를 똑바로 입력해라 인간.");
			return;
		}
		try {
			articleId = Integer.parseInt(cmdDiv[2]);
		} catch (NumberFormatException e) {
			System.out.println("정수를 똑바로 입력해라 인간.");
			return;
		}
		boolean status = asv.remove(conn, articleId);

		if (status == false) {
			System.out.println(articleId + "번 게시글은 없습니다.");
			return;
		}

		System.out.printf("%d번 게시글이 삭제되었습니다.\n", articleId);

>>>>>>> 7db270959f17c47689049647e1234489900231f0
	}

}
