package com.chat.websocket.service;

import com.chat.websocket.dto.response.MessageResponse;
import com.chat.websocket.entity.Conversation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService<T> {
    List<T> findAll();

    T findByID(int id);

    T saveReturnSaved(T t);

    void save(T t);

    MessageResponse deleteByID(int id);

}
