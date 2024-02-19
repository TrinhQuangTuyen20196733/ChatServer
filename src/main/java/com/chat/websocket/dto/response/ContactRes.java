package com.chat.websocket.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactRes {

  public String name;

  public String avatarLocation;

  public String email;

  public LocalDateTime lastActivity;

  public String lastMessage;
}
