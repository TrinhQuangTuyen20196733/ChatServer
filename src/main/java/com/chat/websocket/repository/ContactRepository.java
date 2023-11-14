package com.chat.websocket.repository;

import com.chat.websocket.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    // JPA CRUD ...

    // Get groupMembers using JOIN FETCH
    @Query("select c from Contact c JOIN FETCH c.groupMembers where c.contactID = :contactID")
    public Contact findContactAndGroupMemberByContactID(@Param("contactID") int contactID);
}
