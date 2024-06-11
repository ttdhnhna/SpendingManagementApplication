package com.project.SpendingManagementApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idadmin;
    private String hoten;
    private static final String email = "thanhdatduong24102001@gmail.com";
    private static final String password = "NDTms1a01ATH";

    public Admin(String hoten, String email) {
        this.hoten = hoten;
    }
    public Admin() {
    }

    public Long getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(Long idadmin) {
        this.idadmin = idadmin;
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
