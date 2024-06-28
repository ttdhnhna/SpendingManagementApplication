package com.project.SpendingManagementApplication.entity;

import org.springframework.web.multipart.MultipartFile;

public class UserDto {
    private Long iduser;
    private Long idtongtien;
    private Long idkhoanchi;
    private Long idkhoanthu;

    private String hoten;
    private String email;
    private String password;

    private String ngaysinh;
    private String quequan;
    private String gt;
    private String sdt;
    private String dantoc;
    private MultipartFile anh;
    
    public UserDto() {
    }

    public UserDto(Long iduser, Long idtongtien, Long idkhoanchi, Long idkhoanthu, String hoten, String email,
            String password, String ngaysinh, String quequan, String gt, String sdt, String dantoc,
            MultipartFile anh) {
        this.iduser = iduser;
        this.idtongtien = idtongtien;
        this.idkhoanchi = idkhoanchi;
        this.idkhoanthu = idkhoanthu;
        this.hoten = hoten;
        this.email = email;
        this.password = password;
        this.ngaysinh = ngaysinh;
        this.quequan = quequan;
        this.gt = gt;
        this.sdt = sdt;
        this.dantoc = dantoc;
        this.anh = anh;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public Long getIdtongtien() {
        return idtongtien;
    }

    public void setIdtongtien(Long idtongtien) {
        this.idtongtien = idtongtien;
    }

    public Long getIdkhoanchi() {
        return idkhoanchi;
    }

    public void setIdkhoanchi(Long idkhoanchi) {
        this.idkhoanchi = idkhoanchi;
    }

    public Long getIdkhoanthu() {
        return idkhoanthu;
    }

    public void setIdkhoanthu(Long idkhoanthu) {
        this.idkhoanthu = idkhoanthu;
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

    public String getDantoc() {
        return dantoc;
    }

    public void setDantoc(String dantoc) {
        this.dantoc = dantoc;
    }

    public MultipartFile getAnh() {
        return anh;
    }

    public void setAnh(MultipartFile anh) {
        this.anh = anh;
    }


}
