package com.chat.websocket.service.impl;

import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.repository.GroupMemberRepository;
import com.chat.websocket.service.GroupMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupMemberServiceImpl implements GroupMemberService {

    private final GroupMemberRepository groupMemberRepository;

    @Override
    public void saveAll(List<GroupMember> groupMembers) {
        groupMemberRepository.saveAll(groupMembers);
    }
}
