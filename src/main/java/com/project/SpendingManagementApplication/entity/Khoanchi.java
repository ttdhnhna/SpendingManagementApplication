package com.project.SpendingManagementApplication.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Khoanchi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idkhoanchi;
    private Long iduser;
    private float tongchi;
    private String ngaychi;

    public Khoanchi() {
    }

    public Long getIdkhoanchi() {
        return idkhoanchi;
    }

    public void setIdkhoanchi(Long idkhoanchi) {
        this.idkhoanchi = idkhoanchi;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public float getTongchi() {
        return tongchi;
    }

    public void setTongchi(float tongchi) {
        this.tongchi = tongchi;
    }

    public String getNgaychi() {
        return ngaychi;
    }

    public void setNgaychi(String ngaychi) {
        this.ngaychi = ngaychi;
    }
}
