package com.project.SpendingManagementApplication.entity;

// import java.util.List;

// import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

// import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iduser;

    // @OneToMany(mappedBy = "idkhoanchi", cascade = CascadeType.ALL)
    // @JsonManagedReference
    // private List<Khoanchi> idkhoanchi;

    // @OneToMany(mappedBy = "idkhoanthu", cascade = CascadeType.ALL)
    // @JsonManagedReference
    // private List<Khoanthu> idkhoanthu;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "idtongtien", referencedColumnName = "idtongtien")
    // @JsonBackReference
    // private Tongtien idtongtien;

    private String hoten;
    private String email;
    private String password;

    private String ngaysinh;
    private String quequan;
    private String gt;
    private String sdt;
    @Lob
    private String anh;

    public User() {
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // public List<Khoanchi> getIdkhoanchi() {
    //     return idkhoanchi;
    // }

    // public void setIdkhoanchi(List<Khoanchi> idkhoanchi) {
    //     this.idkhoanchi = idkhoanchi;
    // }

    // public List<Khoanthu> getIdkhoanthu() {
    //     return idkhoanthu;
    // }

    // public void setIdkhoanthu(List<Khoanthu> idkhoanthu) {
    //     this.idkhoanthu = idkhoanthu;
    // }

    // public Tongtien getIdtongtien() {
    //     return idtongtien;
    // }

    // public void setIdtongtien(Tongtien idtongtien) {
    //     this.idtongtien = idtongtien;
    // }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    
}
