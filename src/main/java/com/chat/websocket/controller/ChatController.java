package com.chat.websocket.controller;

import com.chat.websocket.dto.request.MessageRequest;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.Message;
import com.chat.websocket.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ChatController {

    private final  MessageService messageService;

    //
    // Websocket
    //
    @GetMapping("/websocket")
    public String websocket() {
        return "chat/chat-home";
    }

    // Connect websockt
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
        createMessage(messageRequest);

        return messageRequest;
    }

    // Announce to cancel connection
    @MessageMapping("/left/{conversationID")
    @SendTo("/conversation/{conversationID}")
    public void sendMessage(@DestinationVariable String conversationID, @Payload Contact contact) {

        // Handler disconnect ===========================================================

        System.out.println("Contact: " +contact.getContactID() + " ,đã join vào Conversation: " + conversationID);
    }


    // Save message is sent websocket
    public void createMessage(MessageRequest messageRequest) {
        Message message = new Message(messageRequest);

        messageService.save(message);
    }

}
