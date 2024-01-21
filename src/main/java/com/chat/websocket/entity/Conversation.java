package com.chat.websocket.entity;

import com.chat.websocket.enum_constant.ConversationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"messages", "groupMembers"})
@Entity
@Table(name = "conversation")
@Builder
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_id")
    private int id;

    @Column(name = "conversation_name")
    private String conversationName;

    @Enumerated(EnumType.STRING)
    private ConversationType conversationType;

    @JsonIgnore
    @OneToMany(mappedBy = "conversation",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST},
            orphanRemoval = true)
    private List<GroupMember> groupMembers;



}
