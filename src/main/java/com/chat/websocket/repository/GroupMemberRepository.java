package com.chat.websocket.repository;

import com.chat.websocket.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Integer> {

    // JPA CRUD ...

    // Find groupMembers by ContactID because it has a OneToMany association
    @Query("select gm from GroupMember gm where gm.contact.contactID = :contactID")
    public List<GroupMember> findGroupMembersByContactID(@Param("contactID") int contactID);

    // Find groupMembers by ConversationID
    @Query("select gm from GroupMember gm where gm.conversation.conversationID = :conversationID")
    public List<GroupMember> findGroupMembersByConversationID(@Param("conversationID") int conversationID);



}
