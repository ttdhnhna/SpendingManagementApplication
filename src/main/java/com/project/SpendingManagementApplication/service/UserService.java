package com.project.SpendingManagementApplication.service;

import com.project.SpendingManagementApplication.entity.Tongtien;
import com.project.SpendingManagementApplication.entity.User;
import com.project.SpendingManagementApplication.entity.UserDto;
import com.project.SpendingManagementApplication.repository.TongtienRepository;
import com.project.SpendingManagementApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
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

        savedTT.setIduser(user);
        ttrepository.save(savedTT);
    }

    public void updateUser(User user, UserDto userDto){
        MultipartFile file = userDto.getAnh();
        if (file != null && !file.isEmpty()) {
            @SuppressWarnings("null")
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            if (filename.contains("..")) {
                throw new IllegalStateException("File không hợp lệ!");
            }
            try {
                user.setAnh(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        user.setHoten(userDto.getHoten());
        user.setNgaysinh(userDto.getNgaysinh());
        user.setQuequan(userDto.getQuequan());
        user.setGt(userDto.getGt());
        user.setDantoc(userDto.getDantoc());
        user.setSdt(userDto.getSdt());
        user.setEmail(userDto.getEmail());
        
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
