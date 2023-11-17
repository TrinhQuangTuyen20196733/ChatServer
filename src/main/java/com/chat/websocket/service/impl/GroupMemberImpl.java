package com.chat.websocket.service.impl;

import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.repository.GroupMemberRepository;
import com.chat.websocket.service.ContactService;
import com.chat.websocket.service.ConversationService;
import com.chat.websocket.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupMemberImpl implements GroupMemberService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private ContactService contactService;
    @Autowired
    @Lazy
    private ConversationService conversationService;

    @Override
    public List<GroupMember> getAllGroupMember() {
        return groupMemberRepository.findAll();
    }

    @Override
    public GroupMember getGroupMemberByID(int id) {
        Optional<GroupMember> groupMemberResult = groupMemberRepository.findById(id);
        GroupMember groupMember = groupMemberResult.get();

        return groupMember;
    }

    @Override
    public GroupMember addGroupMemberAndReturnGroupMemberSaved(GroupMember groupMember) {
        GroupMember groupMemberSaved = groupMemberRepository.save(groupMember);

        return groupMemberSaved;
    }

    @Override
    public void addGroupMember(GroupMember groupMember) {
        groupMemberRepository.saveAndFlush(groupMember);
    }

    @Override
    public GroupMember deleteGroupMemberByID(int id) {
        Optional<GroupMember> groupMemberResult = groupMemberRepository.findById(id);

        if (groupMemberResult.isPresent()) {
            groupMemberRepository.deleteById(id);

            return groupMemberResult.get();
        }

        return null;
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
    public GroupMember createGroupMemberByConversationIDAndContactID(GroupMember groupMember, int conversationID, int contactID) {
        Conversation conversation = conversationService.getConversationByID(conversationID);
        Contact contact = contactService.getContactByID(contactID);

        groupMember.setConversation(conversation);
        groupMember.setContact(contact);

         if (addGroupMemberAndReturnGroupMemberSaved(groupMember) != null) {
             return addGroupMemberAndReturnGroupMemberSaved(groupMember);
         }

         return null;
    }
}
