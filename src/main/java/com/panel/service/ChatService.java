package com.panel.service;

import java.util.List;

import com.panel.entity.Message;
import com.panel.entity.MyChat;
import com.panel.entity.User;
import com.panel.exceptionHandler.MessageException;
import com.panel.exceptionHandler.UserException;
import com.panel.request.GroupChatRequest;

public interface ChatService {
	
	public MyChat userChat(User requser, Integer userId2) throws MessageException, UserException  ;
	
	public MyChat findById(Integer catId);
	
	public List<MyChat> chatFindByUserId(Integer userId);
	
	public MyChat deleteChat(Integer chatId, Integer userId);
	
	public MyChat createGroup(GroupChatRequest req, User requser);
	
	public MyChat adduserToGroup(Integer chatId ,Integer userId, User request);
	
	public MyChat renameGroup(Integer chatId , String name, User user) throws UserException;
	
	public MyChat removeFromGroup(Integer chatId , Integer userId, User user);

	MyChat findChatById(int chatId);
	
}
