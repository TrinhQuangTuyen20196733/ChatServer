package com.chat.websocket.service;

import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConversationService extends ChatService<Conversation>{

    // public void createConversationWithMessages();

    // public void createConversationWithGroupMember();

    // Contact -> GroupMember -> Conversation
    public List<Conversation> findConversationsByContactID(int contactID);

    // Find conversation by group member
    public Conversation findConversationByGroupMemberID(int groupMemberID);

}
