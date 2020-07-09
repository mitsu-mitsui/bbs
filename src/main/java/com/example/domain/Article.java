package com.example.domain;

import java.util.List;

/**
 * 記事のドメインクラス.
 * 
 * @author hiroto.kitamura
 *
 */
public class Article {
	/**
	 * データベース上のID.
	 */
	private Integer id;
	/**
	 * 投稿者名.
	 */
	private String name;
	/**
	 * 投稿内容.
	 */
	private String content;
	/**
	 * 記事についたいいねの数.
	 */
	private Integer fav;
	/**
	 * 記事についたコメントのリスト.
	 */
	private List<Comment> commentList;

	public Article() {
		// TODO Auto-generated constructor stub
	}

	public Article(Integer id, String name, String content, Integer fav, List<Comment> commentList) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.fav = fav;
		this.commentList = commentList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public Integer getFav() {
		return fav;
	}

	public void setFav(Integer fav) {
		this.fav = fav;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + ", fav=" + fav + ", commentList="
				+ commentList + "]";
	}
}