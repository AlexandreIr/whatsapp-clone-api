package com.aftech.wppclone.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatResponse {
    private String id;
    private String name;
    private long unreadMessages;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private boolean isUserOnline;
    private String senderId;
    private String receiverId;

}
