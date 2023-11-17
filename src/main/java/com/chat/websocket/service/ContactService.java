package com.chat.websocket.service;

import com.chat.websocket.entity.Contact;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService{

    List<Contact> getAllContact();

    Contact getContactByID(int id);

    Contact addContactAndReturnContactSaved(Contact Contact);

    void addContact(Contact contact);

    Contact deleteContactByID(int id);

    // public void createContactWithGroupMember();

    public Contact findContactByGroupMemberID(int groupMemberID);

    public List<Contact> findContactsByConversationID(int conversationID);
}
