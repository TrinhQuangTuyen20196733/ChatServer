package com.chat.websocket.service.impl;

import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.Message;
import com.chat.websocket.repository.MessageRepository;
import com.chat.websocket.service.ConversationService;
import com.chat.websocket.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationService conversationService;

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message findByID(int id) {
        Optional<Message> messageResult = messageRepository.findById(id);
        Message message = messageResult.get();

        return message;
    }

    @Override
    public Message saveReturnSaved(Message message) {
        Message messageSaved = messageRepository.save(message);

        return messageSaved;
    }

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void deleteByID(int id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Message findLatestMessageByConversationID(int conversationID) {
        Message message = messageRepository.findLatestMessageByConversationID(conversationID);

        return message;
    }

    @Override
    public List<Message> findMessagesByConversationID(int conversationID) {
        List<Message> messages = messageRepository.findMessageByConversationID(conversationID);

        return messages;
    }

    @Override
    public void createMessageByConversationID(Message message, int conversationID) {
        Conversation conversation = conversationService.findByID(conversationID);

        message.setConversation(conversation);

        messageRepository.save(message);
    }
}
