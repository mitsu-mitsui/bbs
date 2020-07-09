package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.repository.ArticleRepository;

/**
 * 記事にいいねを付けるコントローラー.
 * 
 * @author hiroto.kitamura
 *
 */
@Controller
@RequestMapping("")
public class FavToArticleController {
	@Autowired
	private ArticleRepository articleRepository;

	/**
	 * いいねを付ける.
	 * 
	 * @param id いいねをつける記事ID
	 * @return 表示するいいねの数が入ったマップ
	 */
	@ResponseBody
	@RequestMapping(value = "addFav", method = RequestMethod.POST)
	synchronized public Map<String, Integer> addFav(String id) {
		Map<String, Integer> map = new HashMap<>();
		articleRepository.addFav(Integer.parseInt(id));
		map.put("fav", articleRepository.loadFav(Integer.parseInt(id)));
		return map;
	}
}
