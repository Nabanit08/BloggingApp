package com.masai.Services;

import com.masai.payloads.CommentDTO;

public interface CommentService {

	CommentDTO createComment(CommentDTO commentDTO,Integer postId,Integer userId);
	void deleteComment(Integer commentId);
	
}
