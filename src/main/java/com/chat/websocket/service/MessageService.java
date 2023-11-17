package com.chat.websocket.service;

import com.chat.websocket.entity.Message;
import com.chat.websocket.entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {


    List<Message> getAllMessage();

    Message getMessageByID(int id);

    Message addMessageAndReturnMessageSaved(Message Message);

    void addMessage(Message contact);

    Message deleteMessageByID(int id);

    // Show on home chat page
    public Message findLatestMessageByConversationID(int conversationID);

    public List<Message> findMessagesByConversationID(int conversationID);

    public void createMessageByConversationID(Message message, int conversationID);
    
}
