package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.impl.CommentServiceImp;
import com.example.payload.CommentDto;
import com.example.response.ApiResponse;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

	@Autowired
	private CommentServiceImp commService;

	@PostMapping("/create/{postId}/{userId}")
	public ResponseEntity<CommentDto> createComm(@RequestBody CommentDto comDto, @PathVariable Integer postId,
			Integer userId) {
		CommentDto createComments = commService.createComments(comDto, postId, userId);
		return new ResponseEntity<CommentDto>(createComments, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{comId}")
	public ResponseEntity<ApiResponse> deleteComments(@PathVariable Integer comId) {
		commService.deleteComment(comId);
		ApiResponse res = new ApiResponse(true, "Delete Comment successfully", null, 200);
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}

}
