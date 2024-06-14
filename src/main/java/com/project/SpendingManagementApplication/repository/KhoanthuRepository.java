package com.project.SpendingManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.SpendingManagementApplication.entity.Khoanthu;
import org.springframework.stereotype.Repository;

@Repository
public interface KhoanthuRepository extends JpaRepository<Khoanthu, Long>{
    
}
