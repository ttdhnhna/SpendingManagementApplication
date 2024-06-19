package com.project.SpendingManagementApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SpendingManagementApplication.entity.Khoanthu;
import com.project.SpendingManagementApplication.repository.KhoanthuRepository;

@Service
public class KhoanthuService {
    @Autowired
    KhoanthuRepository repository;

    public List<Khoanthu> getKhoanthu(){
        return this.repository.findAll();
    }

    public void saveKhoanthu(Khoanthu khoanthu){
        this.repository.save(khoanthu);
    }

    public Khoanthu getKhoanthubyID(long id){
        Optional<Khoanthu> optional = repository.findById(id);
        Khoanthu khoanthu = null;
        if(optional.isPresent()){
            khoanthu=optional.get();
        }else{
            throw new RuntimeException("Không tìm thấy ID khoan chi: "+id);
        }
        return khoanthu;
    }

    public void deleteKhoanthubyID(long id){
        this.repository.deleteById(id);
    }
}
