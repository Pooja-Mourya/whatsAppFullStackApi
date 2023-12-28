package com.example.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Comments;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.exceptionHandler.ResouseNotFoundException;
import com.example.payload.CommentDto;
import com.example.payload.PostDto;
import com.example.payload.UserDto;
import com.example.repository.CommentRepository;
import com.example.service.CommentService;

@Service
public class CommentServiceImp implements CommentService {

	@Autowired
	private CommentRepository commRepo;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private PostServiceImpl postService;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComments(CommentDto comments, Integer userId, Integer postId) {
//		User userById = userService.userById(userId);
//		Post postById = postService.postById(postId);
//		Comments map = modelMapper.map(comments, Comments.class);
//		map.setPost(postById);
//		map.setUser(userById);
//		map.setContent(comments.getContent());
//		Comments save = commRepo.save(map);
//		CommentDto commentDto = modelMapper.map(save, CommentDto.class);
//		return commentDto;
		return null;
	}

	@Override
	public void deleteComment(Integer commId) {
		Comments comments = commRepo.findById(commId).orElseThrow(()->new ResouseNotFoundException("Comments", "id", null));
		commRepo.delete(comments);
	}

}
