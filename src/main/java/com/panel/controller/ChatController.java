package com.panel.controller;

import com.panel.entity.Message;
import com.panel.entity.MyChat;
import com.panel.entity.User;
import com.panel.exceptionHandler.UserException;
import com.panel.payload.ApiResponse;
import com.panel.request.GroupChatRequest;
import com.panel.request.SingleChatRequest;
import com.panel.service.ChatService;
import com.panel.service.ChatServiceImpl;
import com.panel.service.MessageService;
import com.panel.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

	@Autowired
	private ChatServiceImpl chatService;

	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/single")
	public ResponseEntity<MyChat> createChatHandler(@RequestBody SingleChatRequest singleChatRequest,
			@RequestHeader("Authorization") String jwt) {
//		System.out.print("chat request before try");
		try {
//			System.out.print("chat request after try");
			User user = userService.findUserProfile(jwt);
//			System.out.println("chat user : " + user.getId() + "single chat : " + singleChatRequest.getUserid());
			MyChat myChat = chatService.userChat(user, singleChatRequest.getUserid());
			return new ResponseEntity<>(myChat, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/group")
	public ResponseEntity<MyChat> createGroupHandler(@RequestBody GroupChatRequest groupChatRequest,
			@RequestHeader("Authorization") String jwt) {
		System.out.println(groupChatRequest.getUserId() + " : group");
		try {
			User user = userService.findUserProfile(jwt);

			List<Integer> userIds = groupChatRequest.getUserId();
			if (userIds == null) {
				// Handle the situation where userIds is null (throw an exception or return an
				// error response)
				throw new UserException("user id not found");
			}

			MyChat myChat = chatService.createGroup(groupChatRequest, user);
			return new ResponseEntity<>(myChat, HttpStatus.CREATED);
		} catch (Exception e) {
			// Handle exceptions appropriately, you might want to return a different
			// HttpStatus
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/single/{chatId}")
	public ResponseEntity<MyChat> findChatByIdHandler(@PathVariable Integer chatId,
			@RequestHeader("Authorization") String jwt) {
		try {
			MyChat myChat = chatService.findById(chatId);
			return new ResponseEntity<>(myChat, HttpStatus.CREATED);
		} catch (Exception e) {
			// Handle exceptions appropriately, you might want to return a different
			// HttpStatus
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/user")
	public ResponseEntity<List<MyChat>> findAllChatByUserIdHandler(@RequestHeader("Authorization") String jwt) {
		try {
			User user = userService.findUserProfile(jwt);
			System.out.print("user" + user.getUsername());
			List<MyChat> myChat = chatService.chatFindByUserId(user.getId());
			return new ResponseEntity<List<MyChat>>(myChat, HttpStatus.CREATED);
		} catch (Exception e) {
			// Handle exceptions appropriately, you might want to return a different
			// HttpStatus
			e.printStackTrace();
			return new ResponseEntity<List<MyChat>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/user/add/{userId}")
	public ResponseEntity<MyChat> addUserToGroupHandler(@PathVariable Integer userId,
			@RequestHeader("Authorization") String jwt) {

		try {
			User user = userService.findUserProfile(jwt);
//            MyChat myChat1 = chatService.findById(userId);
			User u = userService.getUserById(userId);
			MyChat myChat = chatService.adduserToGroup(u.getId(), userId, user);
			return new ResponseEntity<MyChat>(myChat, HttpStatus.CREATED);
		} catch (Exception e) {
			// Handle exceptions appropriately, you might want to return a different
			// HttpStatus
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/user/remove/{userId}")
	public ResponseEntity<MyChat> removeUserFromGroupHandler(@PathVariable Integer userId,
			@RequestHeader("Authorization") String jwt) {
		try {
			User user = userService.findUserProfile(jwt);
			MyChat myChat1 = chatService.findById(userId);
			System.out.println(myChat1.getId() + " : chatId");
			MyChat myChat = chatService.removeFromGroup(myChat1.getId(), userId, user);
			return new ResponseEntity<MyChat>(myChat, HttpStatus.CREATED);
		} catch (Exception e) {
			// Handle exceptions appropriately, you might want to return a different
			// HttpStatus
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteChatHandler(@PathVariable Integer userId,
			@RequestHeader("Authorization") String jwt) {
		try {
			User user = userService.findUserProfile(jwt);
			chatService.deleteChat(userId, user.getId());

			ApiResponse apiRes = new ApiResponse("Chat delete successfully", true);
			return new ResponseEntity<ApiResponse>(apiRes, HttpStatus.CREATED);
		} catch (Exception e) {
			// Handle exceptions appropriately, you might want to return a different
			// HttpStatus
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{catId}")
	public MyChat findById(@PathVariable Integer catId) {
		return chatService.findById(catId);
	}
 
	@GetMapping("/chatFindByUserId/{userId}")
	public List<MyChat> chatFindByUserId(@PathVariable Integer userId) {
		return chatService.chatFindByUserId(userId);
	}

	@DeleteMapping("/{chatId}/{userId}")
	public ResponseEntity<MyChat> deleteChat(@PathVariable Integer chatId, @PathVariable Integer userId) {
		MyChat deletedChat = chatService.deleteChat(chatId, userId);

		if (deletedChat != null) {
			return new ResponseEntity<>(deletedChat, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or you can use HttpStatus.FORBIDDEN for unauthorized
																// deletion.
		}
	}
}
