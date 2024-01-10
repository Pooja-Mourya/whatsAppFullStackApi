package com.panel.service;

import com.panel.entity.Message;
import com.panel.entity.MyChat;
import com.panel.entity.User;
import com.panel.exceptionHandler.MessageException;
import com.panel.exceptionHandler.UserException;
import com.panel.payload.ChatMessagesResponse;
import com.panel.payload.ProcessedMessage;
import com.panel.repository.ChatRepository;
import com.panel.repository.MessageRepository;
import com.panel.request.SendMessageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepo;
    
    @Autowired
    private UserService userService;
    
    @Autowired 
    private ChatService chatService;
    
    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Override
    public List<ProcessedMessage> getAllMessages(Integer chatId, User reqUser) throws UserException {
        MyChat chat = chatService.findById(chatId);
        logger.info("{}: chat id", chat.getId());

        if (!chat.getUsers().contains(reqUser)) {
            throw new UserException("You are not related to this chat");
        }

        List<Message> messages = messageRepo.findByChat_Id(chatId);

        List<ProcessedMessage> processedMessages = new ArrayList<>();
        
        for (Message sms : messages) {
            // Process each message here
//            System.out.println("Message Content: " + sms.getContent());
//            System.out.println("Timestamp: " + sms.getTimestamp());
//            System.out.println("chat: " + sms.getChat());
//            System.out.println("user: " + sms.getUser());
//            System.out.println("id: " + sms.getId());

            // Create an object to store the processed data
            ProcessedMessage processedMessage = new ProcessedMessage();
            processedMessage.setContent(sms.getContent());
            processedMessage.setTimestamp(sms.getTimestamp());
            processedMessage.setChat(sms.getChat());
            processedMessage.setUser(sms.getUser());
            processedMessage.setId(sms.getId());

            // Add the processed message to the list
            processedMessages.add(processedMessage);
            System.out.println("" + processedMessages.add(processedMessage));
        }

        // Create the response object
        ChatMessagesResponse response = new ChatMessagesResponse();
        response.setMessages(messages);
//        response.setProcessedMessages(processedMessages);
        response.setChatId(String.valueOf(chat.getId()));

        System.out.println("response chat  " + response);
        return processedMessages;
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
		 logger.info("{}: chat id", request.getChatId());
		
		MyChat chat = chatService.findById(request.getChatId());
        logger.info("{}: chat id", chat);
        
		Message message = new Message();
		message.setChat(chat);
		message.setUser(userById);
		message.setContent(request.getContent());
		message.setTimestamp(LocalDateTime.now());
		Message save = messageRepo.save(message);
		return save;
	}
}
