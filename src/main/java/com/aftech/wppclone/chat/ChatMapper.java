package com.aftech.wppclone.chat;

import org.springframework.stereotype.Service;

@Service
public class ChatMapper {
    public ChatResponse toChatResponse(Chat c, String userId) {
        return ChatResponse.builder()
                .id(c.getId())
                .name(c.getChatName(userId))
                .unreadMessages(c.getUnreadMessages(userId))
                .lastMessage(c.getLastMessage())
                .isUserOnline(c.getRecipient().isUserOnline())
                .senderId(c.getSender().getId())
                .receiverId(c.getRecipient().getId())
                .build();
    }
}
