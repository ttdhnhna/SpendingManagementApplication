package com.project.SpendingManagementApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SpendingManagementApplication.entity.ExpenseStatistic;
import com.project.SpendingManagementApplication.repository.ExpenseStatisticRepository;

@Service
public class ExpenseStatisticService {
    @Autowired
    ExpenseStatisticRepository repository;

    public List<ExpenseStatistic> getES(){
        return repository.findAll();
    }

    public void saveES(ExpenseStatistic es){
        this.repository.save(es);
    }

    public ExpenseStatistic getESbyID(long id){
        Optional<ExpenseStatistic> optional = repository.findById(id);
        ExpenseStatistic es = null;
        if(optional.isPresent()){
            es=optional.get();
        }else{
            throw new RuntimeException("Không tìm thấy ID tổng tiền: "+id);
        }
        return es;
    }

    public void deleteESbyID(long id){
        this.repository.deleteById(id);
    }
}
