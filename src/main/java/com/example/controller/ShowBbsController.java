package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;

/**
 * 記事一覧を表示するコントローラー.
 * 
 * @author hiroto.kitamura
 *
 */
@Controller
@RequestMapping("")
public class ShowBbsController {
	@Autowired
	private ArticleRepository articleRepository;

	@ModelAttribute
	private ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}

	@ModelAttribute
	private CommentForm setUpCommentForm() {
		return new CommentForm();
	}

	/**
	 * 記事一覧を表示.
	 * 
	 * @param model リクエストスコープ
	 * @return 記事一覧ページ
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		if (articleList.size() == 0) {
			model.addAttribute("noArticle", "記事がありません　投稿してね");
		}
		model.addAttribute("articleList", articleList);
		return "bbs";
	}
}
