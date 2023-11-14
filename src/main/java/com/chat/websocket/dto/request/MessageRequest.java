package com.chat.websocket.dto.request;

import com.chat.websocket.entity.Message;
import com.chat.websocket.enum_constant.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

    private String messageContent;

    private MessageType messageType;

    private String mediaLocation;

    private String status;

    private Timestamp creationTime;

    private int senderID;

    private int receiverID;

    public MessageRequest(Message message) {
        this.messageContent = message.getMessageContent();
        this.messageType = message.getMessageType();
        this.mediaLocation = message.getMediaLocation();
        this.status = message.getStatus();
        this.creationTime = message.getCreationTime();
        this.senderID = message.getSenderID();
        this.receiverID = message.getReceiverID();
    }
}
