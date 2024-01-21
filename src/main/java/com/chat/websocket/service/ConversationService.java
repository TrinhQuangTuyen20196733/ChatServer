package com.chat.websocket.service;

import com.chat.websocket.dto.request.CreateConversationReq;

public interface ConversationService {
    void createConversation(CreateConversationReq createConversationReq);

    void deleteById(int conversationID);
}
