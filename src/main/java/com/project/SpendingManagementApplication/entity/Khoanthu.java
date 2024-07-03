package com.project.SpendingManagementApplication.entity;

import java.util.Date;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.ManyToOne;


@Entity
@Table(name = "tblIncome")
public class Khoanthu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idkhoanthu;

     @ManyToOne
     @JoinColumn(name = "iduser", nullable = false, referencedColumnName = "iduser")
     @JsonBackReference
     private User iduser;

     @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "idctthu", referencedColumnName = "idctthu")
     @JsonBackReference
     private CTKhoanthu idctthu;

    private float tongthu;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date ngaythu;

    private float tongtien;

    @PrePersist
    public void onUpdate(){
        this.ngaythu = new Date();
    }

    public Khoanthu() {
    }

    public Long getIdkhoanthu() {
        return idkhoanthu;
    }

    public void setIdkhoanthu(Long idkhoanthu) {
        this.idkhoanthu = idkhoanthu;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    public float getTongthu() {
        return tongthu;
    }

    public void setTongthu(float tongthu) {
        this.tongthu = tongthu;
    }

     public CTKhoanthu getIdctthu() {
         return idctthu;
     }

     public void setIdctthu(CTKhoanthu idctthu) {
         this.idctthu = idctthu;
     }

    public Date getNgaythu() {
        return ngaythu;
    }

    public void setNgaythu(Date ngaythu) {
        this.ngaythu = ngaythu;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

}
