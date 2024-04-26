package com.FinalProject.FinalProjectBackend.Service;

import com.FinalProject.FinalProjectBackend.Entity.Admin;
import com.FinalProject.FinalProjectBackend.Entity.Customer;
import com.FinalProject.FinalProjectBackend.Repository.AdminRepository;
import com.FinalProject.FinalProjectBackend.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository administratorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        Admin administrator = administratorRepository.findByUsername(username);

        if (customer != null) {
            return new User(customer.getUsername(), customer.getPassword(), new ArrayList<>());
        } else if (administrator != null) {
            return new User(administrator.getUsername(), administrator.getPassword(), new ArrayList<>());
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}