package com.chat.websocket.repository;

import com.chat.websocket.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    // JPA CRUD ...

    // Find messages by ConversationID
    @Query("select m from Message m where m.conversation.conversationID = :conversationID")
    public List<Message> findMessageByConversationID(@Param("conversationID") int conversationID);

    // Get most recent message
    @Query("SELECT m from Message m where m.conversation.conversationID = :conversationID ORDER BY m.creationTime DESC limit 1")
    public Message findLatestMessageByConversationID(@Param("conversationID") int conversationID);


}

