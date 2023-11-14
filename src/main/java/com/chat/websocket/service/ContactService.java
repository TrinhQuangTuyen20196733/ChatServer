package com.chat.websocket.service;

import com.chat.websocket.entity.Contact;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService extends ChatService<Contact>{

    // public void createContactWithGroupMember();

    public Contact findContactByGroupMemberID(int groupMemberID);

    public List<Contact> findContactsByConversationID(int conversationID);
}
