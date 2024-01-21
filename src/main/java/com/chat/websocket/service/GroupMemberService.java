package com.chat.websocket.service;

import com.chat.websocket.entity.GroupMember;

import java.util.List;

public interface GroupMemberService {
    void saveAll(List<GroupMember> groupMembers);
}
