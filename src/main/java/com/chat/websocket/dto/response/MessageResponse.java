package com.chat.websocket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MessageResponse {
    public int status = 200;
    public String message = "Successfully!";
}
