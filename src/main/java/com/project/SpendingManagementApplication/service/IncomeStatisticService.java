package com.project.SpendingManagementApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SpendingManagementApplication.entity.IncomeStatistic;
import com.project.SpendingManagementApplication.repository.IncomeStatisticRepository;

@Service
public class IncomeStatisticService {
    @Autowired
    IncomeStatisticRepository repository;

    public List<IncomeStatistic> getIS(){
        return repository.findAll();
    }

    public void saveIS(IncomeStatistic is){
        this.repository.save(is);
    }

    public IncomeStatistic getISbyID(long id){
        Optional<IncomeStatistic> optional = repository.findById(id);
        IncomeStatistic is = null;
        if(optional.isPresent()){
            is=optional.get();
        }else{
            throw new RuntimeException("Không tìm thấy ID thống kê: "+id);
        }
        return is;
    }

    public void deleteISbyID(long id){
        this.repository.deleteById(id);
    }
}
