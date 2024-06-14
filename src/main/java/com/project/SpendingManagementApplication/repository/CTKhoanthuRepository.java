package com.project.SpendingManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.SpendingManagementApplication.entity.CTKhoanthu;
import org.springframework.stereotype.Repository;

@Repository
public interface CTKhoanthuRepository extends JpaRepository<CTKhoanthu, Long>{
    
}
