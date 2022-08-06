package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentDto;
import com.blog.services.implementations.CommentServiceImplementation;

@RestController
@RequestMapping("/api/")
public class CommentController {
	@Autowired
	private CommentServiceImplementation commentService;
	
	@PostMapping("/user/{userId}/post/{postId}/comment")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer userId,@PathVariable Integer postId) {
		CommentDto savedComment = this.commentService.createComment(commentDto, userId, postId);
		return new ResponseEntity<CommentDto>(savedComment,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/comment/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,@PathVariable Integer commentId){
		CommentDto updatedComment = this.commentService.updateComment(commentDto, commentId);
		return ResponseEntity.ok(updatedComment);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully", true),HttpStatus.OK);
	}
	
	
}
