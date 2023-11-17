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
    public List<Message> getAllMessage() {
        return messageRepository.findAll();
    }

    @Override
    public Message getMessageByID(int id) {
        Optional<Message> messageResult = messageRepository.findById(id);
        Message message = messageResult.get();

        return message;
    }

    @Override
    public Message addMessageAndReturnMessageSaved(Message message) {
        Message messageSaved = messageRepository.save(message);

        return messageSaved;
    }

    @Override
    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public Message deleteMessageByID(int id) {
        Optional<Message> messageResult = messageRepository.findById(id);

        if (messageResult.isPresent()) {
            messageRepository.deleteById(id);

            return messageResult.get();
        }

        return null;

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
        Conversation conversation = conversationService.getConversationByID(conversationID);

        message.setConversation(conversation);

        messageRepository.save(message);
    }
}
