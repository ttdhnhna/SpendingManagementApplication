package com.project.SpendingManagementApplication.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblIncomeDetail")
public class CTKhoanthu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idctthu;

    @OneToOne(mappedBy = "idctthu")
    @JsonManagedReference
    private Khoanthu idkhoanthu;
    
    private float tongthu;
    private String theloai;
    private String ghichu;

    public CTKhoanthu() {
    }

    public Long getIdctthu() {
        return idctthu;
    }

    public void setIdctthu(Long idctthu) {
        this.idctthu = idctthu;
    }

    public float getTongthu() {
        return tongthu;
    }

    public void setTongthu(float tongthu) {
        this.tongthu = tongthu;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public Khoanthu getIdkhoanthu() {
        return idkhoanthu;
    }

    public void setIdkhoanthu(Khoanthu idkhoanthu) {
        this.idkhoanthu = idkhoanthu;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    
}
