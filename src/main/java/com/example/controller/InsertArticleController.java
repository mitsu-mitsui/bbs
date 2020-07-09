package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;

/**
 * 記事を投稿するコントローラー.
 * 
 * @author hiroto.kitamura
 *
 */
@Controller
@RequestMapping("")
public class InsertArticleController {
	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private HttpSession session;

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

	/**
	 * 記事を投稿.
	 * 
	 * @param articleForm 投稿フォームの入力内容
	 * @return 記事一覧にリダイレクト
	 */
	@RequestMapping("insertArticle")
	public String insertArticle(@Validated ArticleForm articleForm, BindingResult result, Model model) {
		session.setAttribute("name", articleForm.getName());
		if (result.hasErrors()) {
			return index(model);
		}
		Article article = new Article();
		BeanUtils.copyProperties(articleForm, article);
		articleRepository.insert(article);
		return "redirect:/";
	}

}
