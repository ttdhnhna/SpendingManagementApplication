package com.project.SpendingManagementApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.SpendingManagementApplication.entity.CustomUserDetail;
import com.project.SpendingManagementApplication.entity.User;
import com.project.SpendingManagementApplication.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findbyEmail(username);
        if(user==null){
            throw new RuntimeException("Khong tim thay nguoi dung");
        }
        return new CustomUserDetail(user);
    }
    
}
