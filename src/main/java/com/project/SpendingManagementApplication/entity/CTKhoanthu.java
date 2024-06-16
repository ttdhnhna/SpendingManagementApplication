package com.project.SpendingManagementApplication.entity;

// import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblIncomeDetail")
public class CTKhoanthu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idctthu;

    // @OneToOne(mappedBy = "idctthu")
    // @JsonManagedReference
    // private Long idkhoanthu;
    
    private float tongthu;
    private String theloai;

    public CTKhoanthu() {
    }

    public Long getIdctthu() {
        return idctthu;
    }

    public void setIdctthu(Long idctthu) {
        this.idctthu = idctthu;
    }

    // public Long getIdkhoanthu() {
    //     return idkhoanthu;
    // }

    // public void setIdkhoanthu(Long idkhoanthu) {
    //     this.idkhoanthu = idkhoanthu;
    // }

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
}
