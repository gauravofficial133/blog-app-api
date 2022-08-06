package com.blog.payloads;

import com.blog.entities.Post;
import com.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
	private int commentId;
	private String content;
//	private PostDto post;
//	private UserDto user;
}
