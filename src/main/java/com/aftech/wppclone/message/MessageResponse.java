package com.aftech.wppclone.message;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {

    private Long id;
    private String content;
    private String senderId;
    private String receiverId;
    private MessageType type;
    private MessageState state;
    private LocalDateTime createdAt;
    private byte[] media;
}
