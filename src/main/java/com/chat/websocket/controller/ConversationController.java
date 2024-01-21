package com.chat.websocket.controller;

import com.chat.websocket.dto.request.CreateConversationReq;
import com.chat.websocket.dto.response.MessageResponse;
import com.chat.websocket.service.ContactService;
import com.chat.websocket.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversations")
@RequiredArgsConstructor
public class ConversationController {
    private final ContactService contactService;
    private final ConversationService conversationService;

    @PostMapping
    public MessageResponse createConversation(@RequestBody CreateConversationReq createConversationReq) {
        MessageResponse ms = new MessageResponse();
        try {
            conversationService.createConversation(createConversationReq);
        } catch (Exception e) {
            ms.code = 5000;
            ms.message = e.getMessage();
        }
        return ms;
    }


    @DeleteMapping("/{conversationID}")
    public MessageResponse deleteConversation(@PathVariable("conversationID") int conversationID) {
        MessageResponse ms = new MessageResponse();
        try {
            conversationService.deleteById(conversationID);
        } catch (Exception ex) {
            ms.code = 5000;
            ms.message = ex.getMessage();
        }
        return ms;
    }
}
