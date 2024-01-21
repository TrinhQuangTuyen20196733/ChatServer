package com.chat.websocket.repository;

import com.chat.websocket.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation,Integer> {
}
