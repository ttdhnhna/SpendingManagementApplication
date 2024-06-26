package com.project.SpendingManagementApplication.service;

import com.project.SpendingManagementApplication.entity.Tongtien;
import com.project.SpendingManagementApplication.entity.User;
import com.project.SpendingManagementApplication.repository.TongtienRepository;
import com.project.SpendingManagementApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;
    @Autowired
    TongtienRepository ttrepository;

    public List<User> getUser(){
        return repository.findAll();
    }

    public void saveUser(User user){
        Tongtien tt = new Tongtien();
        Tongtien savedTT = ttrepository.save(tt);
        user.setIdtongtien(savedTT);
        this.repository.save(user);
    }

    public void updateUser(User user){
        this.repository.save(user);
    }

    public User getUserbyID(long id){
        Optional<User> optional = repository.findById(id);
        User user = null;
        if(optional.isPresent()){
            user = optional.get();
        }else {
            throw new RuntimeException("Không tìm thấy ID người dùng: "+id);
        }
        return user;
    }

    public void deleteUserbyID(long id){
        this.repository.deleteById(id);
    }
}
