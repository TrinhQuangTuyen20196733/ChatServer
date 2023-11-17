package com.chat.websocket.service.impl;

import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.repository.ContactRepository;
import com.chat.websocket.repository.GroupMemberRepository;
import com.chat.websocket.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Override
    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactByID(int id) {
        Optional<Contact> contactResult = contactRepository.findById(id);
        Contact contact = new Contact();

        // Check if contact exists
        if (contactResult.isPresent()) {
            contact = contactResult.get();;
        } else {
            contact = null;
        }

        return contact;
    }

    @Override
    public Contact addContactAndReturnContactSaved(Contact contact) {
        Contact contactSaved =contactRepository.save(contact);
        return contactSaved;
    }

    @Override
    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public Contact deleteContactByID(int id) {
        Optional<Contact> contactResult = contactRepository.findById(id);

        if (contactResult.isPresent()) {
            contactRepository.deleteById(id);
            return contactResult.get();
        }

        return null;
    }

    @Override
    public Contact findContactByGroupMemberID(int groupMemberID) {
        // Get GroupMember
        Optional<GroupMember> groupMemberResult = groupMemberRepository.findById(groupMemberID);

        // Check if contact exists
        if(groupMemberResult.isPresent()) {
            GroupMember groupMember = groupMemberResult.get();

            // Get Contact
            int contactID = groupMember.getContact().getContactID();
            Contact contact = getContactByID(contactID);

            return contact;
        } else {
            return null;
        }
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
