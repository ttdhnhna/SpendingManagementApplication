package com.project.SpendingManagementApplication.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tblBalances")
public class Tongtien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtongtien;

    @OneToOne(mappedBy = "idtongtien")
    @JsonManagedReference
    private User iduser;

    private float tongtien;
    private float no;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaycapnhat;

    @PrePersist
    @PreUpdate
    public void onUpdate(){
        this.ngaycapnhat = new Date();
    } 

    public Tongtien() {
    }
    public Long getIdtongtien() {
        return idtongtien;
    }
    public void setIdtongtien(Long idtongtien) {
        this.idtongtien = idtongtien;
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
    public float getNo() {
        return no;
    }
    public void setNo(float no) {
        this.no = no;
    }

    public Date getNgaycapnhat() {
        return ngaycapnhat;
    }

    public void setNgaycapnhat(Date ngaycapnhat) {
        this.ngaycapnhat = ngaycapnhat;
    }
    
}
