package com.project.SpendingManagementApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SpendingManagementApplication.entity.CTKhoanchi;
import com.project.SpendingManagementApplication.entity.Khoanchi;
import com.project.SpendingManagementApplication.repository.KhoanchiRepository;

@Service
public class KhoanchiService {
    @Autowired
    KhoanchiRepository repository;
    @Autowired
    CTKhoanchiService ctservice;

    public List<Khoanchi> getKhoanchi(){
        return repository.findAll();
    }

    public void saveKhoanchi(Khoanchi khoanchi, String ghichu, String theloai){
        CTKhoanchi ct=new CTKhoanchi();
        ct.setGhichu(ghichu);
        ct.setTheloai(theloai);
        ct.setIdkhoanchi(khoanchi);
        ct.setTongchi(khoanchi.getTongchi());
        ctservice.saveCTKhoanchi(ct);
        khoanchi.setIdctchi(ct);
        this.repository.save(khoanchi);
    }

    public void updateKhoanchi(Khoanchi khoanchi, String ghichu, String theloai){
        if(khoanchi.getIdctchi()==null){
            throw new RuntimeException("Khoản chi đang không có chi tiết");
        }
        CTKhoanchi ct=ctservice.getCTKhoanchibyID(khoanchi.getIdctchi().getIdctchi());
        if(ct==null){
            throw new RuntimeException("Không tìm thấy id của chi tiết khoản chi: " + khoanchi.getIdctchi().getIdctchi());
        }
        ct.setGhichu(ghichu);
        ct.setTheloai(theloai);
        ct.setTongchi(khoanchi.getTongchi());
        ctservice.saveCTKhoanchi(ct);
        this.repository.save(khoanchi);
    }

    public Khoanchi getKhoanchibyID(long id){
        Optional<Khoanchi> optional = repository.findById(id);
        Khoanchi khoanchi = null;
        if(optional.isPresent()){
            khoanchi=optional.get();
        }else{
            throw new RuntimeException("Không tìm thấy ID khoản chi: "+id);
        }
        return khoanchi;
    }

    public void deleteKhoanchibyID(long id){
        this.repository.deleteById(id);
    }
}
