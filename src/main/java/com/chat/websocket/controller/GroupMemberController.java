package com.chat.websocket.controller;

import com.chat.websocket.dto.request.*;
import com.chat.websocket.dto.response.MessageResponse;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.entity.Message;
import com.chat.websocket.service.ContactService;
import com.chat.websocket.service.ConversationService;
import com.chat.websocket.service.GroupMemberService;
import com.chat.websocket.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//@Controller
@RestController
@RequestMapping("/api/chat")
public class GroupMemberController {
   @Autowired
   private ContactService contactService;
   @Autowired
   private ConversationService conversationService;
   @Autowired
   private GroupMemberService groupMemberService;
   @Autowired
   private MessageService messageService;

    //
    // GroupMember
    //
    // Get group member by conversation id
    @GetMapping("/group-members/conversation/{conversationID}")
    public List<GroupMemberRequest> groupMemberList(@PathVariable("conversationID") int conversationID) {
        // Group members request
        List<GroupMemberRequest> groupMemberRequests = new ArrayList<>();

        // Find group member list by conversation id
        List<GroupMember> groupMembers = groupMemberService.findGroupMembersByConversationID(conversationID);

        if (groupMembers != null) {
            for (GroupMember groupMember :
                    groupMembers) {
                // firstname, lastname, avatarLocation of group member request
                String firstName= "";
                String lastName = "";
                String avatarLocation = "";

                // Find contact by group member id
                Contact contact = contactService.findContactByGroupMemberID(groupMember.getGroupMemberID());
                firstName = contact.getFirstName();
                lastName = contact.getLastName();
                avatarLocation = contact.getAvatarLocation();

                // Creeat groupMemberRequest
                GroupMemberRequest groupMemberRequest = new GroupMemberRequest(groupMember, firstName, lastName, avatarLocation);

                groupMemberRequests.add(groupMemberRequest);
            }

            return groupMemberRequests;
        }

        return null;
    }

    // Add new group member to conversation
    @PostMapping("/group-member")
    public MessageResponse addGroupMemberToConversation(@RequestBody @Valid AddGroupMemberRequest addGroupMemberRequest) {
        // get current time
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());

        // GroupMember
        GroupMember groupMember = new GroupMember();
        groupMember.setJoinTime(currentTimeStamp);

        // save GroupMember
        if (groupMemberService.createGroupMemberByConversationIDAndContactID(groupMember, addGroupMemberRequest.getConversationID(), addGroupMemberRequest.getContactID()) != null) {
            return new MessageResponse(200, "New group member added successfully!");
        }

        return new MessageResponse(500, "Server error!");
    }

    // Delete group member from conversation
    @DeleteMapping("/group-member/{groupMemberID}")
    public MessageResponse deleteGroupMember(@PathVariable("groupMemberID") int groupMemberID) {
        if (groupMemberService.deleteGroupMemberByID(groupMemberID) != null) {
            return new MessageResponse(200, "Group member deleted successfully!");
        }

        return new MessageResponse(404, "Group member does not exists");
    }

}
