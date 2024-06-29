package com.project.SpendingManagementApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SpendingManagementApplication.entity.Tongtien;
import com.project.SpendingManagementApplication.repository.TongtienRepository;

@Service
public class TongtienService {
    @Autowired
    TongtienRepository repository;
    @Autowired
    UserService userService;
    
    public List<Tongtien> getTT(){
        return repository.findAll();
    }

    public void saveTT(Tongtien tongtien){
        this.repository.save(tongtien);
    }

    public Tongtien getTongtienbyID(long id){
        Optional<Tongtien> optional = repository.findById(id);
        Tongtien tongtien = null;
        if(optional.isPresent()){
            tongtien=optional.get();
        }else{
            throw new RuntimeException("Không tìm thấy ID tổng tiền: "+id);
        }
        return tongtien;
    }

    public void deleteTTbyID(long id){
        this.repository.deleteById(id);
    }
}
