package com.project.SpendingManagementApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SpendingManagementApplication.entity.CTKhoanthu;
import com.project.SpendingManagementApplication.entity.Khoanthu;
import com.project.SpendingManagementApplication.entity.Tongtien;
import com.project.SpendingManagementApplication.repository.CTKhoanthuRepository;
import com.project.SpendingManagementApplication.repository.KhoanthuRepository;
import com.project.SpendingManagementApplication.repository.TongtienRepository;

@Service
public class KhoanthuService {
    @Autowired
    KhoanthuRepository repository;
    @Autowired
    CTKhoanthuRepository ctrepository;
    @Autowired
    UserService uservice;
    @Autowired
    TongtienRepository ttrepository;

    public List<Khoanthu> getKhoanthu(){
        return this.repository.findAll();
    }

    public void saveKhoanthu(Khoanthu khoanthu, String ghichu, String theloai){
        Tongtien tt = uservice.getUserbyID(1).getIdtongtien();

        CTKhoanthu ct = new CTKhoanthu();
        ct.setGhichu(ghichu);
        ct.setTheloai(theloai);
        ct.setTongthu(khoanthu.getTongthu());
        
        CTKhoanthu savedCT = ctrepository.save(ct);

        tt.setTongtien(tt.getTongtien()+khoanthu.getTongthu());
        if(tt.getTongtien()<0){
            tt.setTongtien(0);
            tt.setNo(0-tt.getTongtien());
        }
        ttrepository.save(tt);

        khoanthu.setIdctthu(savedCT);
        khoanthu.setIduser(uservice.getUserbyID(1));
        this.repository.save(khoanthu);

        savedCT.setIdkhoanthu(khoanthu);
        ctrepository.save(savedCT);
    }

    public void updateKhoanthu(Khoanthu khoanthu, CTKhoanthu ct){
        Tongtien tt = uservice.getUserbyID(1).getIdtongtien();
        
        if(ct==null){
            throw new RuntimeException("Không tìm thấy id của chi tiết khoản thu: " + khoanthu.getIdctthu().getIdctthu());
        }
        ct.setTongthu(khoanthu.getTongthu());
        ctrepository.save(ct);
        
        tt.setTongtien(tt.getTongtien()+khoanthu.getTongthu());
        if(tt.getTongtien()<0){
            tt.setTongtien(0);
            tt.setNo(0-tt.getTongtien());
        }
        ttrepository.save(tt);

        khoanthu.setIduser(uservice.getUserbyID(1));
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
