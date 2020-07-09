package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
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
			return "forward:/";
		}
		Article article = new Article();
		BeanUtils.copyProperties(articleForm, article);
		articleRepository.insert(article);
		return "redirect:/";
	}

}
