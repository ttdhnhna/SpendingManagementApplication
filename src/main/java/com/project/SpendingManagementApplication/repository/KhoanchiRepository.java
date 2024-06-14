package com.project.SpendingManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.SpendingManagementApplication.entity.Khoanchi;
import org.springframework.stereotype.Repository;

@Repository
public interface KhoanchiRepository extends JpaRepository<Khoanchi, Long>{

}
