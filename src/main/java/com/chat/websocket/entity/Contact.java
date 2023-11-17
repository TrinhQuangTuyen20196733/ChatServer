package com.chat.websocket.entity;

import com.chat.websocket.dto.request.ContactRequest;
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
@Entity
@Table(name = "contact")
@ToString(exclude = "groupMembers")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int contactID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "avatar_location")
    private String avatarLocation;

    @JsonIgnore
    @OneToMany(mappedBy = "contact",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            orphanRemoval = true)
    private List<GroupMember> groupMembers;

    public void addGroupMember(GroupMember tempGroupMember) {
        if (groupMembers == null) {
            groupMembers = new ArrayList<>();
        }

        groupMembers.add(tempGroupMember);

        tempGroupMember.setContact(this);
    }

    public Contact(ContactRequest contactRequest) {
        this.contactID = contactRequest.getContactID();
        this.firstName = contactRequest.getFirstName();
        this.lastName = contactRequest.getLastName();
        this.email = contactRequest.getEmail();
        this.phoneNumber = contactRequest.getPhoneNumber();
        this.avatarLocation = contactRequest.getAvatarLocation();
    }
}
