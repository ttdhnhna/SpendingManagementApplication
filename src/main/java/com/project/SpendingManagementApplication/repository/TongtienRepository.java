package com.project.SpendingManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.SpendingManagementApplication.entity.Tongtien;
import org.springframework.stereotype.Repository;

@Repository
public interface TongtienRepository extends JpaRepository<Tongtien, Long>{
    
}
