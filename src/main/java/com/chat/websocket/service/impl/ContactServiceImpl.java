package com.chat.websocket.service.impl;

import com.chat.websocket.dto.request.UploadAvatarReq;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.exception.BusinessLogicException;
import com.chat.websocket.repository.ContactRepository;
import com.chat.websocket.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void uploadAvatar(UploadAvatarReq uploadAvatarReq) {
        Contact contact = contactRepository.findByEmail(uploadAvatarReq.email).orElseThrow(()-> new BusinessLogicException());
        contact.setAvatarLocation(uploadAvatarReq.avatarLocation);
        contactRepository.save(contact);

    }


}
