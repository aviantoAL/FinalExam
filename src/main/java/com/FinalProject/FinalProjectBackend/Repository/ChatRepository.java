package com.FinalProject.FinalProjectBackend.Repository;

import com.FinalProject.FinalProjectBackend.Entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findBySenderId(Long senderId);
    List<Chat> findByReceiverId(Long receiverId);
}