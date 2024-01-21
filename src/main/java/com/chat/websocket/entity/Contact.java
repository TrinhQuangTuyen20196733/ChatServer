package com.chat.websocket.entity;

import com.chat.websocket.dto.request.ContactRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact")
@ToString(exclude = "groupMembers")
@Builder
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String name;

    @Column(name = "email")
    private String email;


    @Column(name = "avatar_location")
    private String avatarLocation;

    @JsonIgnore
    @OneToMany(mappedBy = "contact",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST},
            orphanRemoval = true)
    private List<GroupMember> groupMembers;

    public Contact(ContactRequest contactRequest) {
       this.name = contactRequest.name;
        this.email = contactRequest.email;
        this.avatarLocation = contactRequest.avatarLocation;
    }
}
