package com.panel.service;

import java.util.List;

import com.panel.entity.Message;
import com.panel.entity.User;
import com.panel.exceptionHandler.MessageException;
import com.panel.exceptionHandler.UserException;
import com.panel.payload.ChatMessagesResponse;
import com.panel.request.SendMessageRequest;

public interface MessageService {
    public Message sendMessage(SendMessageRequest request) throws MessageException, UserException;
    public ChatMessagesResponse getAllMessages(Integer chatId, User reqUser) throws MessageException, UserException;
    public Message findMessageById(Integer messageId) throws MessageException;
    public void deleteMessage(Integer id, User reqUser) throws MessageException;
}
