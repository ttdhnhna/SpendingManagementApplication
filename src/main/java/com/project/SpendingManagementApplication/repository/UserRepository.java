package com.project.SpendingManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.SpendingManagementApplication.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
