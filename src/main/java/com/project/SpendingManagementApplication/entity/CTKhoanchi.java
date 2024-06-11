package com.project.SpendingManagementApplication.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CTKhoanchi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idctchi;
    private Long idkhoanchi;
    private float tongchi;
    private String ghichu;
    private String theloai;

    public CTKhoanchi() {
    }

    public Long getIdctchi() {
        return idctchi;
    }

    public void setIdctchi(Long idctchi) {
        this.idctchi = idctchi;
    }

    public Long getIdkhoanchi() {
        return idkhoanchi;
    }

    public void setIdkhoanchi(Long idkhoanchi) {
        this.idkhoanchi = idkhoanchi;
    }

    public float getTongchi() {
        return tongchi;
    }

    public void setTongchi(float tongchi) {
        this.tongchi = tongchi;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }
}
