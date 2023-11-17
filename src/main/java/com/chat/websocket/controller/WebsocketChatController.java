package com.chat.websocket.controller;

import com.chat.websocket.dto.request.MessageRequest;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.Message;
import com.chat.websocket.service.ConversationService;
import com.chat.websocket.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RestController
public class WebsocketChatController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private ConversationService conversationService;

    //
    // Websocket
    //
    @GetMapping("/websocket")
    public String websocket() {
        return "chat/chat-home";
    }

    // Connect websocket
    @MessageMapping("/join/{conversationID}")
    public void joinConversation(@DestinationVariable String conversationID, @Payload Contact contact) {
        System.out.println("Contact: " + contact.getContactID() + " ,đã join vào Conversation: " + conversationID);
    }

    // Send message and save
    @MessageMapping("/chat/{conversationID}")
    @SendTo("/conversation/{conversationID}")
    public MessageRequest sendMessage(@DestinationVariable String conversationID, @Payload MessageRequest messageRequest) {
        System.out.println("Message request: " +messageRequest);

        // Save message in database
        createMessageInConversation(messageRequest, Integer.parseInt(conversationID));

        return messageRequest;
    }

    // Announce to cancel connection
    @MessageMapping("/left/{conversationID")
    @SendTo("/conversation/{conversationID}")
    public void sendMessage(@DestinationVariable String conversationID, @Payload Contact contact) {

        // Handler disconnect ===========================================================

        System.out.println("Contact: " +contact.getContactID() + " ,đã join vào Conversation: " + conversationID);
    }

    //
    // Service
    //
    // Save message is sent websocket
    public void createMessageInConversation(MessageRequest messageRequest, int conversationID) {
        // Identify conversation
        Conversation conversation = conversationService.getConversationByID(conversationID);

        // Message
        Message message = new Message(messageRequest);
        message.setConversation(conversation);

        messageService.addMessage(message);
    }

}
