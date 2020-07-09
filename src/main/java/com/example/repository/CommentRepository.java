package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * コメント情報を操作するリポジトリ．
 * 
 * @author yuiko.mitsui
 *
 */
@Repository
public class CommentRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	@Autowired
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
		Comment comment = new Comment(rs.getInt("id"), rs.getString("name"), rs.getString("content"),
				rs.getInt("article_id"));

		return comment;
	};

	/**
	 * 記事に対するコメントを取得．
	 * 
	 * @param articleId 記事ID
	 * @return 記事に対する全コメント情報
	 */
	public List<Comment> findByArticleId(Integer articleId) {

		String sql = "SELECT id,name,content,article_id" + " FROM comments"
				+ " WHERE article_id = :articleId ORDER BY id DESC;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);

		return commentList;
	}

	/**
	 * コメントをDBに挿入する．
	 * 
	 * @param comment コメント情報
	 */
	public void insert(Comment comment) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);

		String sql = "INSERT INTO comments(name,content,article_id)" + " VALUES (:name,:content,:articleId);";

		template.update(sql, param);

	}

	/**
	 * 記事に対する全コメントを削除する．
	 * 
	 * @param articleId コメント元の記事ID．
	 */
	public void deleteByArticleId(Integer articleId) {
		String sql = "DELETE FROM comments WHERE article_id =:articleId";

		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(sql, param);
	}

	/**
	 * 消したいコメントを削除する．
	 * 
	 * @param commentId コメントのID
	 */
	public void deleteByCommentId(Integer commentId) {
		String sql = "DELETE FROM comments WHERE id =:commentId";

		SqlParameterSource param = new MapSqlParameterSource().addValue("commentId", commentId);
		template.update(sql, param);
	}
}
