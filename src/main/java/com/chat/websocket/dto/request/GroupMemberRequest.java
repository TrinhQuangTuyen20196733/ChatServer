package com.chat.websocket.dto.request;


import com.chat.websocket.entity.GroupMember;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupMemberRequest {

    private int groupMemberID;
    private String firstName;
    private String lastName;
    private String avatarLocation;

    public GroupMemberRequest(GroupMember groupMember, String firstName, String lastName, String avatarLocation) {
        this.groupMemberID = groupMember.getGroupMemberID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarLocation = avatarLocation;
    }
}
