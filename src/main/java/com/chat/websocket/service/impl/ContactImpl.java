package com.chat.websocket.service.impl;

import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.repository.ContactRepository;
import com.chat.websocket.repository.GroupMemberRepository;
import com.chat.websocket.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactImpl implements ContactService {
    private final ContactRepository contactRepository;

    private final GroupMemberRepository groupMemberRepository;

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact findByID(int id) {
        Optional<Contact> contactResult = contactRepository.findById(id);
        Contact contact = contactResult.get();;

        return contact;
    }

    @Override
    public Contact saveReturnSaved(Contact contact) {
        Contact contactSaved =contactRepository.save(contact);
        return contactSaved;
    }

    @Override
    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void deleteByID(int id) {
        contactRepository.deleteById(id);
    }

    @Override
    public Contact findContactByGroupMemberID(int groupMemberID) {
        // Get GroupMember
        Optional<GroupMember> groupMemberResult = groupMemberRepository.findById(groupMemberID);
        GroupMember groupMember = groupMemberResult.get();

        // Get Contact
        int contactID = groupMember.getContact().getContactID();
        Contact contact = findByID(contactID);

        return contact;
    }

    @Override
    public List<Contact> findContactsByConversationID(int conversationID) {
        List<Contact> contacts = new ArrayList<>();

        List<GroupMember> groupMembers = groupMemberRepository.findGroupMembersByConversationID(conversationID);

        for (GroupMember groupMember:
             groupMembers) {
            Contact contact = findContactByGroupMemberID(groupMember.getGroupMemberID());

            contacts.add(contact);
        }

        return contacts;
    }


}
