package com.project.SpendingManagementApplication.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tblExpense")
public class Khoanchi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idkhoanchi;

    @ManyToOne
    @JoinColumn(name = "iduser", nullable = false, referencedColumnName = "iduser")
    @JsonBackReference
    private User iduser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idctchi", referencedColumnName = "idctchi")
    @JsonBackReference
    private CTKhoanchi idctchi; 

    private float tongchi;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaychi;

    @PrePersist
    @PreUpdate
    public void onUpdate(){
        this.ngaychi = new Date();
    }

    public Khoanchi() {
    }

    public Long getIdkhoanchi() {
        return idkhoanchi;
    }

    public void setIdkhoanchi(Long idkhoanchi) {
        this.idkhoanchi = idkhoanchi;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    public float getTongchi() {
        return tongchi;
    }

    public void setTongchi(float tongchi) {
        this.tongchi = tongchi;
    }

    public CTKhoanchi getIdctchi() {
        return idctchi;
    }

    public void setIdctchi(CTKhoanchi idctchi) {
        this.idctchi = idctchi;
    }

    public Date getNgaychi() {
        return ngaychi;
    }

    public void setNgaychi(Date ngaychi) {
        this.ngaychi = ngaychi;
    }

    
}
