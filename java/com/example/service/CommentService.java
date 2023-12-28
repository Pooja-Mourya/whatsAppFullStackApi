package com.example.service;

import com.example.payload.CommentDto;

public interface CommentService {

	CommentDto createComments(CommentDto comments, Integer userId, Integer postId);
	void deleteComment(Integer commId);
	
}
