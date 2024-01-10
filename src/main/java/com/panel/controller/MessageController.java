package com.panel.controller;

import com.panel.entity.Message;
import com.panel.entity.User;
import com.panel.payload.ApiResponse;
import com.panel.payload.ProcessedMessage;
import com.panel.request.SendMessageRequest;
import com.panel.service.MessageService;
import com.panel.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/create")
	public ResponseEntity<Message> sendMessageHandler(@RequestBody SendMessageRequest sendMessageRequest,
			@RequestHeader("Authorization") String jwt) {
		try {
			User user = userService.findUserProfile(jwt);
			sendMessageRequest.setUserId(user.getId());
			Message sendMessage = messageService.sendMessage(sendMessageRequest);
			return new ResponseEntity<>(sendMessage, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/chat/{chatId}")
	public List<ProcessedMessage> findMesssageByIdHandler(@PathVariable Integer chatId,
			@RequestHeader("Authorization") String jwt) {
		try {
			User user = userService.findUserProfile(jwt);
		return	messageService.getAllMessages(chatId, user);
//			return new ResponseEntity<List<Message>>( HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
	//		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@DeleteMapping("/{chatId}")
	public ResponseEntity<ApiResponse> deleteMessageHandler(@PathVariable Integer chatId,
			@RequestHeader("Authorization") String jwt) {
		try {
			User user = userService.findUserProfile(jwt);
			messageService.deleteMessage(chatId, user);
			ApiResponse apiRes = new ApiResponse("chat deleted successfully", true);
			return new ResponseEntity<>(apiRes, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			ApiResponse apiDelete = new ApiResponse(e.getMessage(), false);
			return new ResponseEntity<>(apiDelete, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
