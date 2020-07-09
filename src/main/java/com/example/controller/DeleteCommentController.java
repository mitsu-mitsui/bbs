package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.repository.CommentRepository;

/**
 * コメントを削除するコントローラ．
 * 
 * @author yuiko.mitsui
 *
 */
@Controller
@RequestMapping("")
public class DeleteCommentController {
	@Autowired
	private CommentRepository commentRepository;
	
	/**
	 * コメントを削除する．
	 * 
	 * @param commentId コメントのID
	 * @return 掲示板
	 */
	@RequestMapping("/deleteComment")
	public String deleteComment (Integer commentId) {
		
		commentRepository.deleteByCommentId(commentId);
		
		return  "redirect:/";
	}
	
}
