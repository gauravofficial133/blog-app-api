package com.blog.services;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto,int userId,int categoryId);
	
	PostDto getPost(int postId);
	
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
		
	PostDto updatePost(PostDto postDto,int postId);
	
	void deletPost(int postId);
	
	List<PostDto> getPostsByCategory(int categoryId);
	
	List<PostDto> getPostsByUser(int userId);
	
	List<PostDto> searchPosts(String keyword);
}
