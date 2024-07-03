package com.project.SpendingManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.SpendingManagementApplication.entity.IncomeStatistic;

@Repository
public interface IncomeStatisticRepository extends JpaRepository<IncomeStatistic, Long>{
    
}
