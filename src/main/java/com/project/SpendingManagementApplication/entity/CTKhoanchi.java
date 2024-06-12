package com.project.SpendingManagementApplication.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblExpenseDetail")
public class CTKhoanchi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idctchi;

    @OneToOne(mappedBy = "idctchi")
    @JsonManagedReference
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
