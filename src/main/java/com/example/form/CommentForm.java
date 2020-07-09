package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	private Integer articleId;
	/**
	 * コメント者名．
	 */
	@NotBlank(message = "名前を入力してください")
	@Size(max = 50, message = "名前は50文字以内で入力してください")
	private String name;
	/**
	 * コメント内容．
	 */
	@NotBlank(message = "内容を入力してください")
	private String Content;

	@Override
	public String toString() {
		return "CommentForm [articleId=" + articleId + ", name=" + name + ", Content=" + Content + "]";
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
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