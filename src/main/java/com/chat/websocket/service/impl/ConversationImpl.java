package com.chat.websocket.service.impl;

import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.repository.ContactRepository;
import com.chat.websocket.repository.ConversationRepository;
import com.chat.websocket.repository.GroupMemberRepository;
import com.chat.websocket.service.ConversationService;
import com.chat.websocket.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationImpl implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;

    private GroupMemberService groupMemberService;
    @Autowired
    public GroupMemberService getGroupMemberService() {
        return groupMemberService;
    }
    public void setGroupMemberService(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }

    @Override
    public List<Conversation> findAll() {
        return conversationRepository.findAll();
    }

    @Override
    public Conversation findByID(int id) {
        Optional<Conversation> conversationResult = conversationRepository.findById(id);
        Conversation conversation = conversationResult.get();

        return conversation;
    }

    @Override
    public Conversation saveReturnSaved(Conversation conversation) {
        Conversation conversationSaved = conversationRepository.save(conversation);
        return conversationSaved;
    }

    @Override
    public void save(Conversation conversation) {
        conversationRepository.save(conversation);
    }

    @Override
    public void deleteByID(int id) {
        conversationRepository.deleteById(id);
    }

    @Override
    public List<Conversation> findConversationsByContactID(int contactID) {
        // Conversation list
        List<Conversation>  conversations = new ArrayList<>();

        // Get GroupMembers by ContactID
        List<GroupMember> groupMembers = groupMemberService.findGroupMembersByContactID(contactID);

        for (GroupMember tempGroupMember:
             groupMembers) {

            // Get Conversation by GroupMember
            Conversation conversation = findConversationByGroupMemberID(tempGroupMember.getGroupMemberID());

            conversations.add(conversation);
        }

        return conversations;
    }

    @Override
    public Conversation findConversationByGroupMemberID(int groupMemberID) {
        // Group member
        Optional<GroupMember> groupMemberResult = groupMemberRepository.findById(groupMemberID);
        GroupMember groupMember = groupMemberResult.get();

        // find Conversation by group member is
        int conversationID = groupMember.getConversation().getConversationID();
        Conversation conversation = findByID(conversationID);

        return conversation;
    }

}

