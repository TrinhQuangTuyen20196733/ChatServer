package com.chat.websocket.service.impl;

import com.chat.websocket.dto.request.AddMemberReq;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.exception.BusinessLogicException;
import com.chat.websocket.repository.GroupMemberRepository;
import com.chat.websocket.service.ContactService;
import com.chat.websocket.service.ConversationService;
import com.chat.websocket.service.GroupMemberService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupMemberServiceImpl implements GroupMemberService {

  private final GroupMemberRepository groupMemberRepository;

  private final ConversationService conversationService;

  private final ContactService contactService;

  @Override
  public void add(AddMemberReq addmemberReq) {
    Conversation conversation = conversationService.getById(addmemberReq.getConversationId());
    List<GroupMember> groupMemberList = addmemberReq.getContactIds().stream()
        .map(contactId -> {
          Contact contact = contactService.findById(contactId);
          return GroupMember.builder()
              .joinTime(LocalDateTime.now())
              .conversation(conversation)
              .contact(contact)
              .build();
        })
        .collect(Collectors.toList());
    groupMemberRepository.saveAll(groupMemberList);
  }

  @Override
  public void saveAll(List<GroupMember> groupMembers) {
    groupMemberRepository.saveAll(groupMembers);
  }

  @Override
  public void deleteMember(long groupMemberId) {
    GroupMember groupMember = groupMemberRepository.findById(groupMemberId).orElseThrow(
        BusinessLogicException::new);
    groupMember.setLeftTime(LocalDateTime.now());
    groupMember.setLastActivity(LocalDateTime.now());
    groupMember.setActive(false);
    groupMemberRepository.save(groupMember);
  }

  @Override
  public void save(GroupMember groupMember) {
    groupMemberRepository.save(groupMember);
  }

}
