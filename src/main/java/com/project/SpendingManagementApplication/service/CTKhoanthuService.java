package com.project.SpendingManagementApplication.service;

import com.project.SpendingManagementApplication.entity.CTKhoanthu;
import com.project.SpendingManagementApplication.repository.CTKhoanthuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CTKhoanthuService {
    @Autowired
    CTKhoanthuRepository repository;

    public List<CTKhoanthu> getCTKhoanthu(){
        return repository.findAll();
    }

    public void saveCTKhoanthu(CTKhoanthu ctKhoanthu){
        this.repository.save(ctKhoanthu);
    }

    public CTKhoanthu getCTKhoanthubyID(long id){
        Optional<CTKhoanthu> optional = repository.findById(id);
        CTKhoanthu ctKhoanthu = null;
        if(optional.isPresent()){
            ctKhoanthu=optional.get();
        }else{
            throw new RuntimeException("Không tìm thấy ID chi tiết khoản thu: "+id);
        }
        return ctKhoanthu;
    }

    public  void deleteCTKhoanthubyID(long id) {
        this.repository.deleteById(id);
    }
}
