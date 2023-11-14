package com.chat.websocket.repository;

import com.chat.websocket.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

    // JPA CRUD ...

    // Get messages using JOIN FETCH
    @Query("select c from Conversation c JOIN FETCH c.messages where c.conversationID = : conversationID")
    public Conversation findConversationAndMessageByConversationID(@Param("conversationID") int conversationID);

    // Get groupMembers using JOIN FETCH
    @Query("select c from Conversation c JOIN FETCH c.groupMembers where c.conversationID = :conversationID")
    public Conversation findConversationAndGroupMemberByConversationID(@Param("conversationID") int conversationID);

}
