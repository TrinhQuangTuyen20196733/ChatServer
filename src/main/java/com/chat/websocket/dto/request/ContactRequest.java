package com.chat.websocket.dto.request;

import com.chat.websocket.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest {

    private int contactID;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String avatarLocation;

    public ContactRequest(Contact contact) {
        this.contactID = contact.getContactID();
        this.firstName = contact.getFirstName();
        this.lastName = contact.getLastName();
        this.email = contact.getEmail();
        this.phoneNumber = contact.getPhoneNumber();
        this.avatarLocation = contact.getAvatarLocation();
    }
}
