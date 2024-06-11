package com.project.SpendingManagementApplication.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Khoanthu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idkhoanthu;
    private Long iduser;
    private float tongthu;
    private String ngaythu;

    public Khoanthu() {
    }

    public Long getIdkhoanthu() {
        return idkhoanthu;
    }

    public void setIdkhoanthu(Long idkhoanthu) {
        this.idkhoanthu = idkhoanthu;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public float getTongthu() {
        return tongthu;
    }

    public void setTongthu(float tongthu) {
        this.tongthu = tongthu;
    }

    public String getNgaythu() {
        return ngaythu;
    }

    public void setNgaythu(String ngaythu) {
        this.ngaythu = ngaythu;
    }
}
