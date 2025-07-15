package com.aftech.wppclone.chat;

import com.aftech.wppclone.user.User;
import com.aftech.wppclone.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatMapper mapper;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ChatResponse> getChatsByReceiverId(Authentication authentication){
        final String userId = authentication.getName();
        return chatRepository.findChatBySenderId(userId)
                .stream()
                .map(c-> mapper.toChatResponse(c, userId)).toList();
    }

    public String createChat(String senderId, String receiverId){
        Optional<Chat> existingChat = chatRepository.findChatByReceiverAndSender(senderId, receiverId);
        if(existingChat.isPresent()){
            return existingChat.get().getId();
        }

        User sender = userRepository.findByPublicId(senderId)
                .orElseThrow(()-> new EntityNotFoundException("User not found with id: "+senderId));

        User receiver = userRepository.findByPublicId(receiverId)
                .orElseThrow(()-> new EntityNotFoundException("User not found with id: "+receiverId));

        Chat chat = new Chat();
        chat.setSender(sender);
        chat.setRecipient(receiver);

        Chat savedChat = chatRepository.save(chat);
        return savedChat.getId();
    }
}
