package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Article;
import com.example.demo.domain.Comment;

/**
 * 記事のリポジトリクラス.
 * 
 * @author hiroto.kitamura
 *
 */
@Repository
public class ArticleRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 記事のResultSet.
	 * 
	 * JOINで結合されたテーブルから記事とそれに付随するコメントリストを取り出す
	 */
	private final static ResultSetExtractor<List<Article>> ARTICLE_RESULTSET = (rs) -> {
		List<Article> articleList = new ArrayList<>();
		Integer prevArticleId = null;
		List<Comment> commentList = null;
		Article article = null;
		while (rs.next()) {
			Integer articleId = rs.getInt("article_id");
			if (articleId != prevArticleId) {
				commentList = new ArrayList<>();
				article = new Article(articleId, rs.getString("article_contributor_name"),
						rs.getString("article_content"), rs.getInt("fav"), commentList);
				articleList.add(article);
				prevArticleId = articleId;
			}
			String commentContent = rs.getString("comment_content");
			if (commentContent != null) {
				Comment comment = new Comment(rs.getInt("comment_id"), rs.getString("comment_contributor_name"),
						commentContent, articleId);
//				articleList.get(articleList.size() - 1).getCommentList().add(comment);
				commentList.add(comment);
//				article.setCommentList(commentList);
			}
		}
		return articleList;
	};

	/**
	 * 記事とコメントを記事ID(投稿日時の昇順に附番)の降順、次いでコメントIDの降順で全件検索.
	 * 
	 * OUTER JOINでコメントがない記事も検索できる
	 * 
	 * @return 記事のリスト
	 */
	public List<Article> findAll() {
		String sql = "SELECT a.id article_id, a.name article_contributor_name"
				+ ", a.content article_content, a.fav, c.id comment_id, c.name comment_contributor_name"
				+ ", c.content comment_content FROM articles a LEFT JOIN comments c"
				+ " ON a.id = c.article_id ORDER BY a.id DESC, c.id;";
		List<Article> articleList = template.query(sql, ARTICLE_RESULTSET);
//		return template.query(sql, ARTICLE_AND_COMMENT_ROWMAPPER);
		return articleList;
	}

	/**
	 * 記事をデータベースに挿入.
	 * 
	 * @param article 挿入する記事
	 */
	public void insert(Article article) {
		String sql = "SELECT max(id) FROM articles;";
		Integer maxId = template.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
		if (maxId == null) {
			article.setId(1);
		} else {
			article.setId(maxId + 1);
		}
		sql = "INSERT INTO articles (id,name,content,fav) VALUES (:id,:name,:content,0);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		template.update(sql, param);
	}

	/**
	 * IDから記事をコメントごと削除.
	 * 
	 * @param id 削除する記事のID
	 */
	public void deleteById(Integer id) {
		String sql = "DELETE FROM articles WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

	/**
	 * 指定された記事のIDを、最も大きいID+1に更新する.
	 * 
	 * @param id IDを更新する記事
	 */
	public void updateId(Integer id) {
		String sql = "SELECT max(id) FROM articles;";
		Integer maxId = template.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
		sql = "UPDATE articles SET id=:newId WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id).addValue("newId",
				id == maxId ? id : maxId + 1);
		template.update(sql, param);
	}

	/**
	 * 指定された記事のいいねを1増やす.
	 * 
	 * @param id 記事のID
	 */
	public void addFav(Integer id) {
		String sql = "UPDATE articles SET fav=fav+1 WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

	/**
	 * 指定された記事のいいねの数を返す.
	 * 
	 * @param id 記事のID
	 * @return いいねの数
	 */
	public Integer loadFav(Integer id) {
		String sql = "SELECT fav FROM articles WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, param, Integer.class);
	}
}
