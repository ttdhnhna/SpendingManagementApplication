package com.project.SpendingManagementApplication.service;

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
    
    public void saveTT(Tongtien tongtien){
        tongtien.setIduser(userService.getUserbyID(1));
        this.repository.save(tongtien);
    }

}
