package com.project.SpendingManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.SpendingManagementApplication.entity.CTKhoanchi;
import org.springframework.stereotype.Repository;

@Repository
public interface CTKhoanchiRepository extends JpaRepository<CTKhoanchi, Long>{
    
}
