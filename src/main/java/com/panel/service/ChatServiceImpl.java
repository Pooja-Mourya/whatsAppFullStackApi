package com.panel.service;

import com.panel.entity.MyChat;
import com.panel.entity.User;
import com.panel.exceptionHandler.ResourceNotFoundException;
import com.panel.exceptionHandler.UserException;
import com.panel.repository.ChatRepository;
import com.panel.repository.UserRepository;
import com.panel.request.GroupChatRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatRepository chatRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserServiceImpl userService;

	@Override
	public MyChat userChat(User requser, Integer userId2) {
		// Implement your userChat logic

		User user = userRepo.findUserById(userId2);
		
//		System.out.println("chat user" + user);
		MyChat chatIsExisting = chatRepo.findSingleChatByUserIds(user, requser);
		
//		System.out.println("existing chat" + chatIsExisting);
		
		if (chatIsExisting != null) {
			return chatIsExisting;
		}
		MyChat chat = new MyChat();
		chat.setCreated_by(requser);
		chat.getUsers().add(requser);
		chat.setName(requser.getUsername());
		chat.setIsgroup(false);
		MyChat save = chatRepo.save(chat);
		return save;
	}

	@Override
	public MyChat findById(Integer catId) {
		return chatRepo.findById(catId).orElse(null);
	}

	@Override
	public List<MyChat> chatFindByUserId(Integer userId) {
        return chatRepo.findByUserId(userId);
	}

	@Override
	public MyChat deleteChat(Integer chatId, Integer userId) {
		// Implement your deleteChat logic
		// Delete the chat identified by chatId and return it
		Optional<MyChat> chatToDelete = chatRepo.findById(chatId);
//        if (chatToDelete.isPresent() && chatToDelete.get().getCreated_by().getId().equals(userId)) {
		chatRepo.deleteById(chatId);
		return chatToDelete.get();
//        } // Chat not found or not authorized for deletion
	}

	@Override
	public MyChat createGroup(GroupChatRequest req, User requser) {
		// TODO Auto-generated method stub
		MyChat group = new MyChat();
		group.setIsgroup(true);
		group.setName(req.getChatName());
		group.setChat_image(req.getChatImage());
		group.setCreated_by(requser);
		for (Integer userId : req.getUserId()) {
			try {
				User user = userService.getUserById(userId);
				group.getUsers().add(user);
			} catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return chatRepo.save(group);
	}

	@Override
	public MyChat adduserToGroup(Integer chatId, Integer userId, User reqUser) {
		
		Optional<MyChat> opt = chatRepo.findById(chatId);
		try {
			User user = userService.getUserById(userId);
			if (opt.isPresent()) {
				MyChat chat = opt.get();

				if (chat.getAdmin().contains(reqUser)) {
					chat.getUsers().add(user);
					return chatRepo.save(chat);
				}

			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new ResourceNotFoundException("chat not found from this id");
	}

	@Override
	public MyChat renameGroup(Integer chatId, String name, User user) throws UserException {
		// TODO Auto-generated method stub
		Optional<MyChat> opt = chatRepo.findById(chatId);
		if (opt.isPresent()) {
			MyChat chat = opt.get();
			if(chat.getUsers().contains(user)){
				chat.setName(name);
				return chatRepo.save(chat);
			};
			throw new UserException("user not found exception");
		}
		throw new UserException("chat user not found exception");
	}

	@Override
	public MyChat removeFromGroup(Integer chatId, Integer userId, User reqUser) {
		Optional<MyChat> opt = chatRepo.findById(userId);
		try {
			User user = userService.getUserById(userId);
			if (opt.isPresent()) {
				MyChat chat = opt.get();
				if (chat.getAdmin().contains(reqUser)) {
					chat.getUsers().remove(user);
					return chatRepo.save(chat);
				}else if(chat.getUsers().contains(reqUser)) {
					if(user.equals(reqUser.getId())) {
						chat.getUsers().remove(user);
						return chatRepo.save(chat);
					}
					throw new ResourceNotFoundException("user not found");
				}
				throw new ResourceNotFoundException("chat user not exist");
			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new ResourceNotFoundException("chat user not found");
	}

	

}
