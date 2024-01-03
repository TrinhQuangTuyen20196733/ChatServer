package com.chat.websocket.dto.request;

import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.entity.Message;
import com.chat.websocket.enum_constant.ConversationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversationRequest {

    private int conversationID;

    private String conversationName;

    private ConversationType conversationType;

    private String latestMessage;

    private List<String> groupMemberAvatarLocations;



    public ConversationRequest(Conversation conversation, String latestMessage, List<String> groupMemberAvatarLocations) {
        this.conversationID = conversation.getConversationID();
        this.conversationName = conversation.getConversationName();
        this.conversationType = conversation.getConversationType();
        this.latestMessage = latestMessage;
        this.groupMemberAvatarLocations = groupMemberAvatarLocations;
    }
}
