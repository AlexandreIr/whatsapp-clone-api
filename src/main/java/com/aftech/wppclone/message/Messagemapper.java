package com.aftech.wppclone.message;

import com.aftech.wppclone.file.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class Messagemapper {
    public MessageResponse toMessageResponse(Message message) {
        return MessageResponse.builder()
                .id(message.getId())
                .content(message.getContent())
                .senderId(message.getSenderId())
                .receiverId(message.getReceiverId())
                .type(message.getType())
                .state(message.getState())
                .createdAt(message.getCreatedDate())
                .media(FileUtils.readFileFromLocation((message.getMediaFilePath())))
                .build();
    }
}
