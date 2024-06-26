package com.project.SpendingManagementApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.SpendingManagementApplication.entity.User;
import com.project.SpendingManagementApplication.service.UserService;

@Controller
public class UserController {
    @Autowired
    UserService service;

    @PostMapping("/saveUser")
    public void saveuser(@RequestBody User user){
        this.service.saveUser(user);
    }

}
