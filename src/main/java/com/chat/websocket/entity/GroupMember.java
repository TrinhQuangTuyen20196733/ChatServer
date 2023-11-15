package com.chat.websocket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "group_member")
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_member_id")
    private int groupMemberID;

    @Column(name = "last_seen")
    private Timestamp lastSeen;

    @Column(name = "join_time")
    private Timestamp joinTime;

    @Column(name = "left_time")
    private Timestamp leftTime;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="contact_id")
    private Contact contact;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="conversation_id")
    private Conversation conversation;
}
