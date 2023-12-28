package com.example.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.config.AppConstant;
import com.example.impl.ImageServiceImpl;
import com.example.impl.PostServiceImpl;
import com.example.payload.PostDto;
import com.example.response.ApiResponse;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostServiceImpl postService;

	@Autowired
	private ImageServiceImpl imageService;

	@Value("${file.upload-dir}")
	private String path;

	@PostMapping(path = "/user/{userId}/category/{catId}/post", consumes = "application/json", produces = "application/json")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId,
			@PathVariable("catId") Integer catId) {
		PostDto post = postService.createPost(postDto, userId, catId);
		return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);
	}

	@PutMapping("/user/{userId}/category/{catId}/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Integer postId,
			@PathVariable("userId") Integer userId, @PathVariable("catId") Integer catId) {
		PostDto updatePost = postService.updatePost(postDto, postId, catId, catId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.ACCEPTED);
	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId) {
		PostDto postById = postService.postById(postId);
		return new ResponseEntity<PostDto>(postById, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/post/delete/{postId}")
	public ResponseEntity<ApiResponse> postDelete(@PathVariable("postId") Integer postId) {
		postService.deletePost(postId);
		ApiResponse resp = new ApiResponse(true, "Post deleted successFully", null, 200);
		return new ResponseEntity<ApiResponse>(resp, HttpStatus.ACCEPTED);
	}

//	@GetMapping("/posts")
//	public ResponseEntity<List<PostDto>> Posts(
//			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
//			@RequestParam(value = "pageSize", defaultValue = "6", required = false) Integer pageSize)
//
//	{
//		List<PostDto> list = postService.allPost(pageNumber, pageSize);
//		return new ResponseEntity<List<PostDto>>(list, HttpStatus.ACCEPTED);
//	}
	@GetMapping("/posts")
	public ResponseEntity<Page<PostDto>> posts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy) {
		Page<PostDto> postPage = postService.allPost(pageNumber, pageSize, sortBy);
		return new ResponseEntity<>(postPage, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}/post")
	public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable("userId") Integer userId) {
		List<PostDto> listByUserId = postService.listByUserId(userId);
		return new ResponseEntity<List<PostDto>>(listByUserId, HttpStatus.ACCEPTED);
	}

	@GetMapping("/category/{catId}/post")
	public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable("catId") Integer catId) {
		List<PostDto> listByCategoryId = postService.listByCategoryId(catId);
		return new ResponseEntity<List<PostDto>>(listByCategoryId, HttpStatus.ACCEPTED);
	}

	@GetMapping("post/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable("keywords") String keywords) {
		List<PostDto> searchByKey = postService.postSerchByKey(keywords);
		return new ResponseEntity<List<PostDto>>(searchByKey, HttpStatus.OK);
	}

	@PostMapping("/post/file/{postId}")
	public ResponseEntity<PostDto> uploadImage(@PathVariable Integer postId, @RequestParam("file") MultipartFile file) throws IOException{
		System.out.println("upload is running");
		PostDto postDto = postService.postById(postId);
		String image = imageService.uploadImage(path, file);
		postDto.setPostImage(image);
		PostDto updatePost = postService.updatePost(postDto, postId, postId, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.ACCEPTED);
	}

	

//	@PostMapping("/upload")
//	public ResponseEntity<Map<String, String>> handleFileUploadUsingCurl(@RequestParam("file") MultipartFile file )
//			throws IOException {
//		Map<String, String> map = new HashMap<>();
//		map.put("fileName", file.getOriginalFilename());
//		map.put("fileContentType", file.getContentType());
//		map.put("message", "File upload done");
//		imageService.uploadImage(path, file);
//		return new ResponseEntity<Map<String, String>>(map , HttpStatus.OK);
//	}

}
