package com.chat.websocket.controller;

import com.chat.websocket.dto.response.ChatMessageRes;
import com.chat.websocket.dto.response.MessageResponse;
import com.chat.websocket.service.MessageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;

  @GetMapping("conversation/{conversationId}")
  public List<ChatMessageRes> getAllMessageConversation(long conversationId) {
    return messageService.getAllMessageConversation(conversationId);
  }

  @DeleteMapping("conversation/{conversationId}")
  public MessageResponse deleteMessageConversation(long messageId) {
    MessageResponse ms = new MessageResponse();
    try {
      messageService.deleteMessageConversation(messageId);
    } catch (Exception ex) {
      ms.message = ex.getMessage();
      ms.code = 5000;
    }
    return ms;

  }
}
