package com.FinalProject.FinalProjectBackend.Service;

import com.FinalProject.FinalProjectBackend.Entity.Order;
import com.FinalProject.FinalProjectBackend.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setId(updatedOrder.getId());
            order.setStatus(updatedOrder.getStatus());
            return orderRepository.save(order);
        } else {
            return null;
        }
    }}


