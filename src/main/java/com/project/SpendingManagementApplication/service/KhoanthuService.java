package com.project.SpendingManagementApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        float thongke=khoanthu.getThongke();

        CTKhoanthu ct = new CTKhoanthu();
        ct.setGhichu(ghichu);
        ct.setTheloai(theloai);
        ct.setTongthu(khoanthu.getTongthu());
        
        CTKhoanthu savedCT = ctrepository.save(ct);
        tinhTongtien(khoanthu,0);

        khoanthu.setIdctthu(savedCT);
        khoanthu.setIduser(uservice.getUserbyID(1));
        thongke += khoanthu.getTongthu();
        khoanthu.setThongke(thongke);
        this.repository.save(khoanthu);

        savedCT.setIdkhoanthu(khoanthu);
        ctrepository.save(savedCT);
    }

    public void updateKhoanthu(Khoanthu khoanthu, CTKhoanthu ct){      
        float ttcu = getKhoanthubyID(khoanthu.getIdkhoanthu()).getTongthu(); 
        float thongke = khoanthu.getThongke(); 
        if(ct==null){
            throw new RuntimeException("Không tìm thấy id của chi tiết khoản thu: " + khoanthu.getIdctthu().getIdctthu());
        }
 
        if(khoanthu.getTongthu()>ttcu){
            thongke += khoanthu.getTongthu() - ttcu;
        }else{
            thongke -= ttcu - khoanthu.getTongthu();
        }
        khoanthu.setThongke(thongke);
        tinhTongtien(khoanthu, khoanthu.getTongthu() - ttcu);

        ct.setTongthu(khoanthu.getTongthu());
        ctrepository.save(ct);

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

    public Page<Khoanthu> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : 
            Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return this.repository.findAll(pageable);
    }

    public void tinhTongtien(Khoanthu khoanthu, float du){
        Tongtien tt = uservice.getUserbyID(1).getIdtongtien();
        tt.setTongtien(tt.getTongtien()+khoanthu.getTongthu()+du);
        if(tt.getTongtien()<0){
            tt.setTongtien(0);
            tt.setNo(0-tt.getTongtien());
        }
        ttrepository.save(tt);
    }
}
