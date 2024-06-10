package com.project.SpendingManagementApplication.entity;

import jakarta.persistence.Entity;

@Entity
public class Admin {
    private String hoten;
    private static final String email = "thanhdatduong24102001@gmail.com";
    private static final String password = "NDTms1a01ATH";

    public Admin(String hoten, String email) {
        this.hoten = hoten;
    }
    public Admin() {
    }
    public String getHoten() {
        return hoten;
    }
    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    
}
