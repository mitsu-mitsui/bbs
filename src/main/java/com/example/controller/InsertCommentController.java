package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Comment;
import com.example.form.CommentForm;
import com.example.repository.CommentRepository;

@Controller
@RequestMapping("")
public class InsertCommentController {
	@Autowired
	private CommentRepository commentRepository;

	/**
	 * コメントを投稿する．
	 * 
	 * @param form コメント投稿フォーム
	 * @return 掲示板画面
	 */
	@RequestMapping("/insertComment")
	public String insertComment(CommentForm form) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);

		comment.setArticleId(Integer.parseInt(form.getArticleId()));

		commentRepository.insert(comment);

		return "redirect:/";
	}
}
