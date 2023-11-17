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
public class MessageController {
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
        List<Message> messages = messageService.findMessagesByConversationID(conversationID);

        if (messages != null) {
            for (Message message :
                    messages) {
                MessageRequest messageRequest = new MessageRequest(message);

                messageRequests.add(messageRequest);
            }

            return messageRequests;
        }

        return null;
    }

    // Delete message
    @DeleteMapping("/message/{messageID}")
    public MessageResponse deleteMessage(@PathVariable("messageID") int messageID) {
        if (messageService.deleteMessageByID(messageID) != null) {
            return new MessageResponse(200, "Message deleted successfully!");
        }

        return new MessageResponse(404, "Message does not exists");
    }

}
