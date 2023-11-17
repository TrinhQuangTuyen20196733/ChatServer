package com.chat.websocket.service;

import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConversationService{

    List<Conversation> getAllConversation();

    Conversation getConversationByID(int id);

    Conversation addConversationAndReturnConversationSaved(Conversation Conversation);

    void addConversation(Conversation contact);

    Conversation deleteConversationByID(int id);

    // public void createConversationWithMessages();

    // public void createConversationWithGroupMember();

    // Conversation -> GroupMember -> Contact
    public List<Conversation> findConversationsByContactID(int contactID);

    // Find conversation by group member
    public Conversation findConversationByGroupMemberID(int groupMemberID);

}
