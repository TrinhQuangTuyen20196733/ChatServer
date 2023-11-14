package com.chat.websocket.service;

import com.chat.websocket.entity.GroupMember;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupMemberService extends ChatService<GroupMember>{

    // Contact -> GroupMember -> Conversation
    public List<GroupMember> findGroupMembersByContactID(int contactID);

    public List<GroupMember> findGroupMembersByConversationID(int conversationID);

    public void createGroupMemberByConversationIDAndContactID(GroupMember groupMember, int conversationID, int contactID);

}
