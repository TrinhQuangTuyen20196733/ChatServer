package com.chat.websocket.entity;

import com.chat.websocket.dto.request.MessageRequest;
import com.chat.websocket.enum_constant.MessageType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")

    private int messageID;

    @Column(name = "message_content")
    private String messageContent;

    @Column(name = "message_type")
    private MessageType messageType;

    @Column(name = "media_location")
    private String mediaLocation;

    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Column(name = "creation_time")
    private Timestamp creationTime;

    @Column(name = "sender_id")
    private int senderID;

    @Column(name = "receiver_id", nullable = false, columnDefinition = "int default 0")
    private int receiverID;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE,
              CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="conversation_id")
    private Conversation conversation;


    public Message(MessageRequest messageRequest) {
        this.messageContent = messageRequest.getMessageContent();
        this.messageType = messageRequest.getMessageType();
        this.mediaLocation = messageRequest.getMediaLocation();
        this.status = messageRequest.getStatus();
        this.creationTime = messageRequest.getCreationTime();
        this.senderID = messageRequest.getSenderID();
        this.receiverID = messageRequest.getReceiverID();
    }
}

