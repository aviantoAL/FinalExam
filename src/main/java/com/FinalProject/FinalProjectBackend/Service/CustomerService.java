package com.FinalProject.FinalProjectBackend.Service;

import com.FinalProject.FinalProjectBackend.Entity.Customer;
import com.FinalProject.FinalProjectBackend.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setUsername(updatedCustomer.getUsername());
            customer.setPassword(updatedCustomer.getPassword());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setPhone_number(updatedCustomer.getPhone_number());
            customer.setReward(updatedCustomer.getReward());
            return customerRepository.save(customer);
        } else {
            return null;
        }
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
