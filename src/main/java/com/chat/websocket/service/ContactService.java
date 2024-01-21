package com.chat.websocket.service;

import com.chat.websocket.dto.request.UploadAvatarReq;
import com.chat.websocket.entity.Contact;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ContactService{


    void save(Contact contact);

    void uploadAvatar(UploadAvatarReq uploadAvatarReq);
}
