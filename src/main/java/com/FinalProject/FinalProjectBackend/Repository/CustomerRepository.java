package com.FinalProject.FinalProjectBackend.Repository;

import com.FinalProject.FinalProjectBackend.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
}