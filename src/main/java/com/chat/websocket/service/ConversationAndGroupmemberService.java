package com.chat.websocket.service;

import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;

import java.util.List;

public interface ConversationAndGroupmemberService {

    // Conversation -> GroupMember -> Contact
    public List<Conversation> findConversationsByContactID(int contactID);

    public Conversation findConversationByGroupMemberID(int groupMemberID);

    public GroupMember createGroupMemberByConversationIDAndContactID(GroupMember groupMember, int conversationID, int contactID);


}
