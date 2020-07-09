package com.example.form;

/**
 * コメント入力フォーム．
 * 
 * @author yuiko.mitsui
 *
 */
public class CommentForm {
	/**
	 * 記事ID．
	 */
	private String articleId;
	/**
	 * コメント者名．
	 */
	private String name;
	/**
	 * コメント内容．
	 */
	private String Content;

	@Override
	public String toString() {
		return "CommentForm [articleId=" + articleId + ", name=" + name + ", Content=" + Content + "]";
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}