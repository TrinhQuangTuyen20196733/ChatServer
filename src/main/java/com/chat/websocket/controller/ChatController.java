package com.chat.websocket.controller;

import com.chat.websocket.dto.request.*;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//@Controller
@RestController
@RequestMapping("/api/chat")
public class ChatController {
   @Autowired
   private ContactService contactService;
   @Autowired
   private ConversationService conversationService;
   @Autowired
   private GroupMemberService groupMemberService;
   @Autowired
   private MessageService messageService;

    //
    // Message
    //
    // Get Message by conversation id
    @GetMapping("/messages/conversation/{conversationID}")
    public List<MessageRequest> messageList(@PathVariable("conversationID") int conversationID) {
        // Messages request
        List<MessageRequest> messageRequests = new ArrayList<>();

        // Find message list by converstion id
       try {
           List<Message> messages = messageService.findMessagesByConversationID(conversationID);

           for (Message message :
                   messages) {
               MessageRequest messageRequest = new MessageRequest(message);

               messageRequests.add(messageRequest);
           }
       } catch (Exception e) {

       }

        return messageRequests;
    }

    // Delete message
    @DeleteMapping("/message/{messageID}")
    public void deleteMessage(@PathVariable("messageID") int messageID) {
        messageService.deleteByID(messageID);
    }

    //
    // GroupMember
    //
    // Get group member by conversation id
    @GetMapping("/group-members/conversation/{conversationID}")
    public List<GroupMemberRequest> groupMemberList(@PathVariable("conversationID") int conversationID) {
        // Group members request
        List<GroupMemberRequest> groupMemberRequests = new ArrayList<>();

        // Find group member list by conversation id
        List<GroupMember> groupMembers = groupMemberService.findGroupMembersByConversationID(conversationID);

        for (GroupMember groupMember :
                groupMembers) {
            // firstname, lastname, avatarLocation of group member request
            String firstName= "";
            String lastName = "";
            String avatarLocation = "";

            // Find contact by group member id
            Contact contact = contactService.findContactByGroupMemberID(groupMember.getGroupMemberID());
            firstName = contact.getFirstName();
            lastName = contact.getLastName();
            avatarLocation = contact.getAvatarLocation();

            // Creeat groupMemberRequest
            GroupMemberRequest groupMemberRequest = new GroupMemberRequest(groupMember, firstName, lastName, avatarLocation);

            groupMemberRequests.add(groupMemberRequest);
        }

        return groupMemberRequests;
    }

    // Add new group member to conversation
    @PostMapping("/group-member/add-group-member")
    public void addGroupMemberToConversation(@RequestParam("conversationID") int conversationID, @RequestParam("contactID") int contactID) {
        // get current time
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());

        // GroupMember
        GroupMember groupMember = new GroupMember();
        groupMember.setJoinTime(currentTimeStamp);

        // save GroupMember
        groupMemberService.createGroupMemberByConversationIDAndContactID(groupMember, conversationID, contactID);
    }

    // Delete group member from conversation
    @DeleteMapping("/group-member/{groupMemberID}")
    public void deleteGroupMember(@PathVariable("groupMemberID") int groupMemberID) {
        groupMemberService.deleteByID(groupMemberID);
    }

    //
    //  Contact
    //
    // Get contact by contact id
    @GetMapping("/contact/{contactID}")
    public  ContactRequest contactByContactID(@PathVariable("contactID") int contactID) {
        Contact contact = contactService.findByID(contactID);

        ContactRequest contactRequest = new ContactRequest(contact);

        return contactRequest;
    }

    // Get contact by group member id
    @GetMapping("/contact/group-member/{groupMemberID}")
    public ContactRequest contactByGroupMember(@PathVariable("groupMemberID") int groupMemberID) {
        // Find contact by group member id
        Contact contact = contactService.findContactByGroupMemberID(groupMemberID);

        // Create new contact request
        ContactRequest contactRequest = new ContactRequest(contact);

        return contactRequest;
    }

    // Create contact
    @PostMapping("/contact/create")
    public void createContact(@RequestBody @Valid ContactRequest contactRequest) {
        Contact contact = new Contact(contactRequest);

        contactService.save(contact);
    }

    // Delete contact ==========================================================================================

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
            System.out.println(conversation.getConversationID());

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
                System.out.println(contact);
                groupMemberAvatarLocations.add(contact.getAvatarLocation());

            }

            // Conversation request
            ConversationRequest conversationRequest = new ConversationRequest(conversation, latestMessage, groupMemberAvatarLocations);
            conversationRequests.add(conversationRequest);
        }

        return conversationRequests;
    }

    // Create new conversation with group members
    @PostMapping("/conversation/create")
    public void createNewConversation(@RequestBody @Valid ConversationAndContactsRequests request) {
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
        Conversation conversationSaved = conversationService.saveReturnSaved(conversation);
//        System.out.println("Conversation: "+conversationSaved.getConversationID());

        // Add group member to new conversation is saved
        for (Contact contact:
             contacts) {
            GroupMember groupMember = new GroupMember();
            groupMember.setConversation(conversationSaved);
            groupMember.setContact(contact);

            groupMemberService.save(groupMember);
        }

        // Test JSON
//        {
//            "conversationRequest": {
//            "conversationID": 6,
//                    "conversationName": "Nhóm QD",
//                    "conversationType": "OneToOne",
//                    "latestMessage": "Xin chào Doanh, mình là Quỳnh",
//                    "groupMemberAvatarLocations": [
//                      "E:\\Project-3\\websocket\\quynh",
//                    "E:\\Project-3\\websocket\\doanh"
//        ]
//        },
//            "contactRequests": [
//            {
//                "contactID": 1,
//                    "firstName": "Quynh",
//                    "lastName": "Pham",
//                    "email": "quynh@gmail.com",
//                    "phoneNumber": "",
//                    "avatarLocation": "E:\\Project-3\\websocket\\quynh"
//            },
//            {
//                "contactID": 5,
//                    "firstName": "Doanh",
//                    "lastName": "Tran",
//                    "email": "doanh@gmail.com",
//                    "phoneNumber": "",
//                    "avatarLocation": "E:\\Project-3\\websocket\\doanh"
//            }
//    ]
//        }
    }

    // Delete conversation =======================================================================================

}
