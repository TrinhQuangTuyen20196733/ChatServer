package com.chat.websocket.service.impl;

import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.repository.ConversationRepository;
import com.chat.websocket.repository.GroupMemberRepository;
import com.chat.websocket.service.ContactService;
import com.chat.websocket.service.ConversationAndGroupmemberService;
import com.chat.websocket.service.ConversationService;
import com.chat.websocket.service.GroupMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationAndGroupmemberImpl implements ConversationAndGroupmemberService {
    private final ContactService contactService;
    private final GroupMemberService groupMemberService;
    private final ConversationService conversationService;

    @Override
    public List<Conversation> findConversationsByContactID(int contactID) {
        // Conversation list
        List<Conversation> conversations = new ArrayList<>();

        // Get GroupMembers by ContactID
        List<GroupMember> groupMembers = groupMemberService.findGroupMembersByContactID(contactID);

        if (groupMembers != null ) {
            for (GroupMember tempGroupMember:
                    groupMembers) {

                // Get Conversation by GroupMember
                Conversation conversation = findConversationByGroupMemberID(tempGroupMember.getGroupMemberID());

                conversations.add(conversation);
            }

            return conversations;
        }

        return null;
    }

    @Override
    public Conversation findConversationByGroupMemberID(int groupMemberID) {
        // Group member
        GroupMember groupMember = groupMemberService.findById(groupMemberID);

        if (groupMember != null) {
            // find Conversation by group member is
            int conversationID = groupMember.getConversation().getConversationID();
            Conversation conversation = conversationService.getConversationByID(conversationID);

            return conversation;
        }

        return null;
    }

    @Override
    public GroupMember createGroupMemberByConversationIDAndContactID(GroupMember groupMember, int conversationID, int contactID) {
        Conversation conversation = conversationService.getConversationByID(conversationID);
        Contact contact = contactService.getContactByID(contactID);

        groupMember.setConversation(conversation);
        groupMember.setContact(contact);

        if (groupMemberService.addGroupMemberAndReturnGroupMemberSaved(groupMember) != null) {
            return groupMemberService.addGroupMemberAndReturnGroupMemberSaved(groupMember);
        }

        return null;
    }
}
