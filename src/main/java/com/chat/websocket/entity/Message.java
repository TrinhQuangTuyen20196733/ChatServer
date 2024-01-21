package com.chat.websocket.entity;

import com.chat.websocket.dto.request.MessageRequest;
import com.chat.websocket.enum_constant.MessageType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "message_type")
    private MessageType messageType;

    @Column(name = "media_location")
    private String mediaLocation;

    @Column(name = "status")
    private int status;

    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDateTime creationTime;




    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_member_id")
    private GroupMember groupMember;



}

