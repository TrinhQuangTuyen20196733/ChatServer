package com.chat.websocket.service;

import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.entity.GroupMember;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupMemberService {


    List<GroupMember> getAllGroupMember();

    GroupMember getGroupMemberByID(int id);

    GroupMember addGroupMemberAndReturnGroupMemberSaved(GroupMember GroupMember);

    void addGroupMember(GroupMember contact);

    GroupMember deleteGroupMemberByID(int id);

    // Contact -> GroupMember -> Conversation
    public List<GroupMember> findGroupMembersByContactID(int contactID);

    public List<GroupMember> findGroupMembersByConversationID(int conversationID);

    public GroupMember createGroupMemberByConversationIDAndContactID(GroupMember groupMember, int conversationID, int contactID);

}
