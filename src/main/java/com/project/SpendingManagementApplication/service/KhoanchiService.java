package com.project.SpendingManagementApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SpendingManagementApplication.entity.CTKhoanchi;
import com.project.SpendingManagementApplication.entity.Khoanchi;
import com.project.SpendingManagementApplication.repository.CTKhoanchiRepository;
import com.project.SpendingManagementApplication.repository.KhoanchiRepository;

@Service
public class KhoanchiService {
    @Autowired
    KhoanchiRepository repository;
    @Autowired
    CTKhoanchiRepository ctrepository;
    @Autowired
    UserService uservice;

    public List<Khoanchi> getKhoanchi(){
        return repository.findAll();
    }

    public void saveKhoanchi(Khoanchi khoanchi, String ghichu, String theloai){
        CTKhoanchi ct=new CTKhoanchi();
        ct.setGhichu(ghichu);
        ct.setTheloai(theloai);
        ct.setTongchi(khoanchi.getTongchi());

        CTKhoanchi savedCT = ctrepository.save(ct);

        khoanchi.setIdctchi(savedCT);
        khoanchi.setIduser(uservice.getUserbyID(1));
        this.repository.save(khoanchi);

        savedCT.setIdkhoanchi(khoanchi);
        ctrepository.save(savedCT);
    }

    public void updateKhoanchi(Khoanchi khoanchi, CTKhoanchi ct){
        if(ct==null){
            throw new RuntimeException("Không tìm thấy id của chi tiết khoản chi: " + khoanchi.getIdctchi().getIdctchi());
        }
        ct.setTongchi(khoanchi.getTongchi());
        ctrepository.save(ct);
        khoanchi.setIduser(uservice.getUserbyID(1));
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
