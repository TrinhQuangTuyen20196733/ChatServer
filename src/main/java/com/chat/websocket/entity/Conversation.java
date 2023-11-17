package com.chat.websocket.entity;

import com.chat.websocket.dto.request.ConversationRequest;
import com.chat.websocket.enum_constant.ConversationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"messages", "groupMembers"})
@Entity
@Table(name = "conversation")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_id")
    private int conversationID;

    @Column(name = "conversation_name")
    private String conversationName;

    @Column(name = "conversation_type")
    private ConversationType conversationType;

    @JsonIgnore
    @OneToMany(mappedBy = "conversation",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<GroupMember> groupMembers;

    @JsonIgnore
    @OneToMany(mappedBy = "conversation",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<Message> messages;

    public void addGroupMember(GroupMember tempGroupMember) {

        if (groupMembers == null) {
            groupMembers = new ArrayList<>();
        }

        groupMembers.add(tempGroupMember);

        tempGroupMember.setConversation(this);
    }

    public void addMessage(Message tempMessage) {

        if (messages == null) {
            messages = new ArrayList<>();
        }

        messages.add(tempMessage);

        tempMessage.setConversation(this);
    }

    public Conversation(ConversationRequest conversationRequest) {
        this.conversationID = conversationRequest.getConversationID();
        this.conversationName = conversationRequest.getConversationName();
        this.conversationType = conversationRequest.getConversationType();
    }
}
