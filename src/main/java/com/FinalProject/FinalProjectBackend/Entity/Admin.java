package com.FinalProject.FinalProjectBackend.Entity;



import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "administrator")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    private String username;
    private String password;
    private String email;
    private String phone_number;

    @OneToMany(mappedBy = "administrator")
    private List<Order> managedOrders;

    @OneToMany(mappedBy = "sender")
    private List<Chat> chats;
}