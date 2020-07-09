package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.repository.ArticleRepository;

/**
 * 記事を削除するコントローラー.
 * 
 * @author hiroto.kitamura
 *
 */
@Controller
@RequestMapping("")
public class DeleteArticleController {

	@Autowired
	private ArticleRepository articleRepository;

	/**
	 * 記事を削除.
	 * 
	 * @param articleId 記事のID hiddenで自動取得
	 * @return 記事一覧にリダイレクト
	 */
	@RequestMapping("deleteArticle")
	public String deleteArticle(String articleId) {
		articleRepository.deleteById(Integer.parseInt(articleId));
		return "redirect:/";
	}

}
