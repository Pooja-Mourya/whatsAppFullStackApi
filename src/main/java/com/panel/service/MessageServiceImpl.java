package com.panel.service;

import com.panel.entity.Message;
import com.panel.entity.MyChat;
import com.panel.entity.User;
import com.panel.exceptionHandler.MessageException;
import com.panel.exceptionHandler.UserException;
import com.panel.payload.ChatMessagesResponse;
import com.panel.repository.MessageRepository;
import com.panel.request.SendMessageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepo;
    
    @Autowired
    private UserService userService;
    
    @Autowired 
    private ChatService chatService;

    @Override
    public ChatMessagesResponse getAllMessages(Integer chatId, User reqUser) throws UserException {
        MyChat chat = chatService.findById(chatId);
        System.out.println(chat.getId() + ": chat id");
        if (!chat.getUsers().contains(reqUser)) {
            throw new UserException("You are not related to this chat");
        }

        List<Message> messages = messageRepo.findByChatId(chatId);
        ChatMessagesResponse response = new ChatMessagesResponse();
        response.setMessages(messages);
        response.setChatId(String.valueOf(chat.getId()));  // or Integer.toString(chat.getId())

        return response;
    }



    @Override
    public Message findMessageById(Integer messageId) throws MessageException {
        Optional<Message> message = messageRepo.findById(messageId);
        if(message.isPresent()) {
        	return message.get();
        }
         throw new MessageException("message not found with id : " + messageId);
    }

    @Override
    public void deleteMessage(Integer id, User reqUser) throws MessageException {
    	Message message = findMessageById(id);
    	if(message.getId().equals(reqUser.getId())) {
    		messageRepo.deleteById(id);
    	}
        throw new MessageException("you can not delete anther user caht");
    }

	@Override
     public Message sendMessage(SendMessageRequest request) throws MessageException, UserException {
		User userById = userService.getUserById(request.getUserId());
		MyChat findById = chatService.findById(request.getChatId());
		
		Message message = new Message();
		message.setChat(findById);
		message.setUser(userById);
		message.setContent(request.getContent());
		message.setTimestamp(LocalDateTime.now());
		
		Message save = messageRepo.save(message);
		return save;
	}
}
