package com.project.SpendingManagementApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SpendingManagementApplication.entity.CTKhoanthu;
import com.project.SpendingManagementApplication.entity.Khoanthu;
import com.project.SpendingManagementApplication.repository.CTKhoanthuRepository;
import com.project.SpendingManagementApplication.repository.KhoanthuRepository;

@Service
public class KhoanthuService {
    @Autowired
    KhoanthuRepository repository;
    @Autowired
    CTKhoanthuRepository ctrepository;

    public List<Khoanthu> getKhoanthu(){
        return this.repository.findAll();
    }

    public void saveKhoanthu(Khoanthu khoanthu, String ghichu, String theloai){
        CTKhoanthu ct = new CTKhoanthu();
        ct.setGhichu(ghichu);
        ct.setTheloai(theloai);
        ct.setTongthu(khoanthu.getTongthu());
        
        CTKhoanthu savedCT = ctrepository.save(ct);

        khoanthu.setIdctthu(savedCT);
        this.repository.save(khoanthu);

        savedCT.setIdkhoanthu(khoanthu);
        ctrepository.save(savedCT);
    }

    public void updateKhoanthu(Khoanthu khoanthu, CTKhoanthu ct){
        if(khoanthu.getIdctthu()==null){
            throw new RuntimeException("Khoản thu đang không có chi tiết");
        }
        if(ct==null){
            throw new RuntimeException("Không tìm thấy id của chi tiết khoản thu: " + khoanthu.getIdctthu().getIdctthu());
        }
        ct.setTongthu(khoanthu.getTongthu());
        ctrepository.save(ct);
        this.repository.save(khoanthu);
    }

    public Khoanthu getKhoanthubyID(long id){
        Optional<Khoanthu> optional = repository.findById(id);
        Khoanthu khoanthu = null;
        if(optional.isPresent()){
            khoanthu=optional.get();
        }else{
            throw new RuntimeException("Không tìm thấy ID khoản chi: "+id);
        }
        return khoanthu;
    }

    public void deleteKhoanthubyID(long id){
        this.repository.deleteById(id);
    }
}
