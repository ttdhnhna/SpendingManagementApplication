package com.project.SpendingManagementApplication.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblIncomeStatistic")
public class IncomeStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idis;

    @OneToOne(mappedBy = "idis")
    @JsonManagedReference
    private User iduser;
    
    private float tongtien;
    
    public IncomeStatistic() {
    }

    public Long getIdis() {
        return idis;
    }

    public void setIdis(Long idis) {
        this.idis = idis;
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
