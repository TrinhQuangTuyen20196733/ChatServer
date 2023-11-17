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

import java.util.ArrayList;
import java.util.List;

//@Controller
@RestController
@RequestMapping("/api/chat")
public class ConversationController {
   @Autowired
   private ContactService contactService;
   @Autowired
   private ConversationService conversationService;
   @Autowired
   private GroupMemberService groupMemberService;
   @Autowired
   private MessageService messageService;

    //
    // Conversation
    //
    // Get conversations by contact id
    @GetMapping("/conversations/contact/{contactID}")
    public List<ConversationRequest> conversationList(@PathVariable("contactID") int contactID) {
        // Conversattions request
        List<ConversationRequest> conversationRequests = new ArrayList<>();

        // Conversations
        List<Conversation> conversations = conversationService.findConversationsByContactID(contactID);

        for (Conversation conversation:
                conversations) {
//            System.out.println(conversation.getConversationID());

            // LatestMessage and groupMemberAvatarLocations
            String latestMessage = "";
            List<String> groupMemberAvatarLocations = new ArrayList<>();

            // Get most recent message of conversation
            try {
                Message message = messageService.findLatestMessageByConversationID(conversation.getConversationID());
                latestMessage = message.getMessageContent();
            } catch (Exception e) {
//                e.printStackTrace();
            }

            // Get groupMember Avatar Locations: conversation => contact
            List<Contact> contacts = contactService.findContactsByConversationID(conversation.getConversationID());
            for (Contact contact :
                    contacts) {
//                System.out.println(contact);
                groupMemberAvatarLocations.add(contact.getAvatarLocation());

            }

            // Conversation request
            ConversationRequest conversationRequest = new ConversationRequest(conversation, latestMessage, groupMemberAvatarLocations);
            conversationRequests.add(conversationRequest);
        }

        return conversationRequests;
    }

    // Create new conversation with group members
    @PostMapping("/conversation")
    public void createNewConversation(@RequestBody @Valid ConversationAndContactsRequest request) {
        // Contacts
        List<Contact> contacts = new ArrayList<>();

        for (ContactRequest contactRequest :
                request.getContactRequests()) {
            Contact contact = new Contact(contactRequest);

            contacts.add(contact);
        }

//        for (Contact contact :
//                contacts) {
//            System.out.println("Contact: "+contact);
//        }

        // Conversation
        Conversation conversation = new Conversation(request.getConversationRequest());
//        System.out.println("Conversation: "+conversation.toString());

        // Create new conversation
        Conversation conversationSaved = conversationService.addConversationAndReturnConversationSaved(conversation);
//        System.out.println("Conversation: "+conversationSaved.getConversationID());

        // Add group member to new conversation is saved
        for (Contact contact:
             contacts) {
            GroupMember groupMember = new GroupMember();
            groupMember.setConversation(conversationSaved);
            groupMember.setContact(contact);

            groupMemberService.addGroupMember(groupMember);
        }

        // Test JSON
//        {
//            "conversationRequest": {
//            "conversationID": "",
//                    "conversationName": "Nhóm QT",
//                    "conversationType": "OneToOne",
//                    "latestMessage": "Xin chào Tuyến, mình là Quỳnh",
//                    "groupMemberAvatarLocations": [
//                      "E:\\Project-3\\websocket\\quynh",
//                    "E:\\Project-3\\websocket\\tuyen"
//        ]
//        },
//            "contactRequests": [
//            {
//                "contactID": 1,
//                    "firstName": "Quynh",
//                    "lastName": "Pham",
//                    "email": "quynh@gmail.com",
//                    "phoneNumber": "123",
//                    "avatarLocation": "E:\\Project-3\\websocket\\quynh"
//            },
//            {
//                "contactID": 2,
//                    "firstName": "Tuyen",
//                    "lastName": "Trinh",
//                    "email": "tuyen@gmail.com",
//                    "phoneNumber": "123",
//                    "avatarLocation": "E:\\Project-3\\websocket\\tuyen"
//            }
//    ]
//        }
    }

    // Delete conversation
    @DeleteMapping("/conversation/{conversationID}")
    public MessageResponse deleteConversation(@PathVariable("conversationID") int conversationID) {
        if (contactService.deleteContactByID(conversationID) != null) {
            return new MessageResponse(200, "Conversation deleted successfully!");
        }

        return new MessageResponse(404, "Conversation does not exists!");
    }

}
