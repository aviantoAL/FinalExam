package com.FinalProject.FinalProjectBackend.Service;

import com.FinalProject.FinalProjectBackend.Entity.Chat;
import com.FinalProject.FinalProjectBackend.Repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    public List<Chat> getChatsBySender(Long senderId) {
        return chatRepository.findBySenderId(senderId);
    }

    public List<Chat> getChatsByReceiver(Long receiverId) {
        return chatRepository.findByReceiverId(receiverId);
    }
}
