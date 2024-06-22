package com.project.SpendingManagementApplication.entity;

import java.util.Date;

 import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tblIncome")
public class Khoanthu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idkhoanthu;

    // @ManyToOne
    // @JoinColumn(name = "iduser", nullable = false, referencedColumnName = "iduser")
    // @JsonBackReference
    // private User iduser;

     @OneToOne
     @JoinColumn(name = "idctthu", referencedColumnName = "idctthu")
     @JsonBackReference
     private CTKhoanthu idctthu;

    private float tongthu;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaythu;

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

    // public User getIduser() {
    //     return iduser;
    // }

    // public void setIduser(User iduser) {
    //     this.iduser = iduser;
    // }

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

    
}
