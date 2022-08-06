package com.blog.services;

import com.blog.payloads.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto,int userId,int postId);
	
	CommentDto updateComment(CommentDto commentDto,int commentId);
	
	void deleteComment(int commentId);
}
