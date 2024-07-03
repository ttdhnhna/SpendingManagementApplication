package com.project.SpendingManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.SpendingManagementApplication.entity.ExpenseStatistic;

@Repository
public interface ExpenseStatisticRepository extends JpaRepository<ExpenseStatistic, Long>{
    
}
