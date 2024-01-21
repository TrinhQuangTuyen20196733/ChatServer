package com.chat.websocket.service.impl;

import com.chat.websocket.dto.request.CreateConversationReq;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.enum_constant.ConversationType;
import com.chat.websocket.exception.BusinessLogicException;
import com.chat.websocket.repository.ContactRepository;
import com.chat.websocket.repository.ConversationRepository;
import com.chat.websocket.service.ConversationService;
import com.chat.websocket.service.GroupMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationServiceImpl implements ConversationService {
    private final ContactRepository contactRepository;
    private  final GroupMemberService groupMemberService;

    private  final ConversationRepository conversationRepository;

    @Override
    public void createConversation(CreateConversationReq createConversationReq) {
        Conversation conversation = Conversation.builder()
                .conversationType(ConversationType.valueOf(createConversationReq.conversationType))
                .conversationName(createConversationReq.conversationName)
                .build();
        List<GroupMember> groupMembers = createConversationReq.getMembers().stream()
                .map(email ->{
                    Contact contact = contactRepository.findByEmail(email).orElseThrow(()->new BusinessLogicException("User : "+email+" doesn't exist"));
                    return  contact;
                })
                .map(contact -> GroupMember.builder()
                        .contact(contact)
                        .conversation(conversation)
                        .joinTime(LocalDateTime.now())
                        .build()
                )
                .collect(Collectors.toList());
        conversation.setGroupMembers(groupMembers);
        conversationRepository.save(conversation);

    }

    @Override
    public void deleteById(int conversationID) {
        conversationRepository.deleteById(conversationID);
    }
}
