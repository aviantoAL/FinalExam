package com.FinalProject.FinalProjectBackend.Controller;

import com.FinalProject.FinalProjectBackend.Entity.Chat;
import com.FinalProject.FinalProjectBackend.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<Chat> createChat(@RequestBody Chat chat) {
        Chat createdChat = chatService.saveChat(chat);
        return new ResponseEntity<>(createdChat, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Chat> getAllChats() {
        return chatService.getAllChats();
    }

    @GetMapping("/sender/{senderId}")
    public List<Chat> getChatsBySender(@PathVariable Long senderId) {
        return chatService.getChatsBySender(senderId);
    }

    @GetMapping("/receiver/{receiverId}")
    public List<Chat> getChatsByReceiver(@PathVariable Long receiverId) {
        return chatService.getChatsByReceiver(receiverId);
    }
}