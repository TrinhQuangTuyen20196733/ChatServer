package com.chat.websocket.service.impl;

import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.repository.GroupMemberRepository;
import com.chat.websocket.service.ContactService;
import com.chat.websocket.service.ConversationService;
import com.chat.websocket.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupMemberImpl implements GroupMemberService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private ContactService contactService;

    private ConversationService conversationService;
    @Autowired
    public void setConversationService(ConversationService conversationService) {
        this.conversationService = conversationService;
    }
    public ConversationService getConversationService() {
        return conversationService;
    }

    @Override
    public List<GroupMember> findAll() {
        return groupMemberRepository.findAll();
    }

    @Override
    public GroupMember findByID(int id) {
        Optional<GroupMember> groupMemberResult = groupMemberRepository.findById(id);
        GroupMember groupMember = groupMemberResult.get();

        return groupMember;
    }

    @Override
    public GroupMember saveReturnSaved(GroupMember groupMember) {
        GroupMember groupMemberSaved = groupMemberRepository.save(groupMember);

        return groupMemberSaved;
    }

    @Override
    public void save(GroupMember groupMember) {
        groupMemberRepository.saveAndFlush(groupMember);
    }

    @Override
    public void deleteByID(int id) {
        groupMemberRepository.deleteById(id);
    }


    @Override
    public List<GroupMember> findGroupMembersByContactID(int contactID) {
       List<GroupMember> groupMembers = groupMemberRepository.findGroupMembersByContactID(contactID);

        return groupMembers;
    }

    @Override
    public List<GroupMember> findGroupMembersByConversationID(int conversationID) {
        List<GroupMember> groupMembers = groupMemberRepository.findGroupMembersByConversationID(conversationID);

        return groupMembers;
    }

    @Override
    public void createGroupMemberByConversationIDAndContactID(GroupMember groupMember, int conversationID, int contactID) {
        Conversation conversation = conversationService.findByID(conversationID);
        Contact contact = contactService.findByID(contactID);

        groupMember.setConversation(conversation);
        groupMember.setContact(contact);

        groupMemberRepository.save(groupMember);
    }
}
