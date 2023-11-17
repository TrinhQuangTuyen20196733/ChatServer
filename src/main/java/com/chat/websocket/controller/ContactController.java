package com.chat.websocket.controller;

import com.chat.websocket.dto.request.*;
import com.chat.websocket.dto.response.MessageResponse;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.entity.Message;
import com.chat.websocket.service.ContactService;
import com.chat.websocket.service.ConversationService;
import com.chat.websocket.service.GroupMemberService;
import com.chat.websocket.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//@Controller
@RestController
@RequestMapping("/api/chat")
public class ContactController {
   @Autowired
   private ContactService contactService;
   @Autowired
   private ConversationService conversationService;
   @Autowired
   private GroupMemberService groupMemberService;
   @Autowired
   private MessageService messageService;

    //
    //  Contact
    //
    // Get contact by contact id
    @GetMapping("/contact/{contactID}")
    public ContactRequest contactByContactID(@PathVariable("contactID") int contactID) {
        Contact contact = contactService.getContactByID(contactID);

        ContactRequest contactRequest = new ContactRequest(contact);

        return contactRequest;
    }

    // Get contact by group member id
    @GetMapping("/contact/group-member/{groupMemberID}")
    public ContactRequest contactByGroupMember(@PathVariable("groupMemberID") int groupMemberID) {
        // Find contact by group member id
        Contact contact = contactService.findContactByGroupMemberID(groupMemberID);

        if (contact != null){
            // Create new contact request
            ContactRequest contactRequest = new ContactRequest();

            return contactRequest;
        }

        return null;
    }

    // Create contact
    @PostMapping("/contact")
    public MessageResponse createContact(@RequestBody @Valid ContactRequest contactRequest) {
        Contact contact = new Contact(contactRequest);

        if (contactService.addContactAndReturnContactSaved(contact) != null) {
            return new MessageResponse(200, "Contact created successfully!");
        }

        return new MessageResponse(500, "Server Error!");
    }

    // Delete contact
    @DeleteMapping("/contact/{contactID}")
    public MessageResponse deleteContact(@PathVariable("contactID") int contactID) {
        if (contactService.deleteContactByID(contactID) != null) {
            return new MessageResponse(200, "Contact deleted successfully!");
        }

        return new MessageResponse(404, "Contact does not exists!");
    }


}
