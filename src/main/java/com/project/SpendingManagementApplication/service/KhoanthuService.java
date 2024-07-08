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
import com.project.SpendingManagementApplication.entity.IncomeStatistic;
import com.project.SpendingManagementApplication.entity.Khoanthu;
import com.project.SpendingManagementApplication.entity.Tongtien;
import com.project.SpendingManagementApplication.entity.User;
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
    TongtienRepository ttrepository;
    @Autowired
    UserService uservice;
    @Autowired
    IncomeStatisticService isservice;

    public List<Khoanthu> getKhoanthu(){
        return this.repository.findAll();
    }

    public void saveKhoanthu(Khoanthu khoanthu, String ghichu, String theloai){
        User user = uservice.getUserbyID(1);
        IncomeStatistic is = isservice.getISbyID(user.getIdis().getIdis());
        float thongke=is.getTongtien();

        CTKhoanthu ct = new CTKhoanthu();
        ct.setGhichu(ghichu);
        ct.setTheloai(theloai);
        ct.setTongthu(khoanthu.getTongthu());
        
        CTKhoanthu savedCT = ctrepository.save(ct);
        float tongtien =  tinhTongtien(khoanthu,0);

        khoanthu.setIduser(user);
        khoanthu.setIdctthu(ct);
        this.repository.save(khoanthu);

        thongke += khoanthu.getTongthu();
        is.setTongtien(thongke);
        isservice.saveIS(is);

        savedCT.setTongtien(tongtien);
        savedCT.setIdkhoanthu(khoanthu);
        ctrepository.save(savedCT);
    }

    public void updateKhoanthu(Khoanthu khoanthu, CTKhoanthu ct){      
        User user = uservice.getUserbyID(1);
        float ttcu = getKhoanthubyID(khoanthu.getIdkhoanthu()).getTongthu(); 
        IncomeStatistic is = isservice.getISbyID(user.getIdis().getIdis());
        float thongke=is.getTongtien();
        if(ct==null){
            throw new RuntimeException("Không tìm thấy id của chi tiết khoản thu: " + khoanthu.getIdctthu().getIdctthu());
        }
 
        if(khoanthu.getTongthu()>ttcu){
            thongke += khoanthu.getTongthu() - ttcu;
        }else{
            thongke -= ttcu - khoanthu.getTongthu();
        }
        is.setTongtien(thongke);
        isservice.saveIS(is);
        float tongtien =  tinhTongtien(khoanthu,khoanthu.getTongthu() - ttcu);

        ct.setTongthu(khoanthu.getTongthu());
        ct.setTongtien(tongtien);
        ctrepository.save(ct);

        khoanthu.setIduser(user);
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
        User user = uservice.getUserbyID(1);
        Khoanthu khoanthu = getKhoanthubyID(id);

        IncomeStatistic is = isservice.getISbyID(user.getIdis().getIdis());
        is.setTongtien(is.getTongtien()-khoanthu.getTongthu());
        isservice.saveIS(is);

        khoanthu.setTongthu(0-khoanthu.getTongthu());
        tinhTongtien(khoanthu, 0);

        ctrepository.deleteById(khoanthu.getIdctthu().getIdctthu());
        this.repository.deleteById(id);
    }

    public Page<Khoanthu> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : 
            Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return this.repository.findAll(pageable);
    }

    public float tinhTongtien(Khoanthu khoanthu, float du){
        Tongtien tt = uservice.getUserbyID(1).getIdtongtien();
        tt.setTongtien(tt.getTongtien()+khoanthu.getTongthu()+du);
        if(tt.getTongtien()<0){
            tt.setTongtien(0);
            tt.setNo(0-tt.getTongtien());
        }
        ttrepository.save(tt);
        return tt.getTongtien();
    }

    public Page<Khoanthu> getPage(Pageable pageable){
        return this.repository.findAll(pageable);
    }
}
