package com.project.SpendingManagementApplication.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblExpenseStatistic")
public class ExpenseStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ides;

    @OneToOne(mappedBy = "ides")
    @JsonManagedReference
    private User iduser;
    
    private float tongtien;

    public ExpenseStatistic() {
    }

    public Long getIdes() {
        return ides;
    }

    public void setIdes(Long ides) {
        this.ides = ides;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }
    
    
}
