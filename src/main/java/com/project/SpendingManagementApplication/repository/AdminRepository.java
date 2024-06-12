package com.project.SpendingManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.SpendingManagementApplication.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
    
}
