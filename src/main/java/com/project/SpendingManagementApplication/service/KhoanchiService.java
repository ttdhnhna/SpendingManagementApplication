package com.project.SpendingManagementApplication.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.SpendingManagementApplication.entity.CTKhoanchi;
import com.project.SpendingManagementApplication.entity.ExpenseStatistic;
import com.project.SpendingManagementApplication.entity.Khoanchi;
import com.project.SpendingManagementApplication.entity.Tongtien;
import com.project.SpendingManagementApplication.entity.User;
import com.project.SpendingManagementApplication.repository.CTKhoanchiRepository;
import com.project.SpendingManagementApplication.repository.KhoanchiRepository;
import com.project.SpendingManagementApplication.repository.TongtienRepository;

@Service
public class KhoanchiService {
    @Autowired
    KhoanchiRepository repository;
    @Autowired
    CTKhoanchiRepository ctrepository;
    @Autowired 
    TongtienRepository ttrepository;
    @Autowired
    UserService uservice;
    @Autowired
    ExpenseStatisticService esservice;


    public List<Khoanchi> getKhoanchi(){
        return repository.findAll();
    }

    public void saveKhoanchi(Khoanchi khoanchi, String ghichu, String theloai){
        User user = uservice.getUserbyID(1);
        ExpenseStatistic es = esservice.getESbyID(user.getIdes().getIdes());
        float thongke= es.getTongtien();

        CTKhoanchi ct=new CTKhoanchi();
        ct.setGhichu(ghichu);
        ct.setTheloai(theloai);
        ct.setTongchi(khoanchi.getTongchi());

        CTKhoanchi savedCT = ctrepository.save(ct);
        float tongtien = tinhTongtien(khoanchi,0);

        khoanchi.setIdctchi(savedCT);
        khoanchi.setIduser(user);
        this.repository.save(khoanchi);

        thongke += khoanchi.getTongchi();
        es.setTongtien(thongke);
        esservice.saveES(es);

        savedCT.setTongtien(tongtien);
        savedCT.setIdkhoanchi(khoanchi);
        ctrepository.save(savedCT);
    }

    public void updateKhoanchi(Khoanchi khoanchi, CTKhoanchi ct){
        User user = uservice.getUserbyID(1);
        float tccu = getKhoanchibyID(khoanchi.getIdkhoanchi()).getTongchi();
        ExpenseStatistic es = esservice.getESbyID(user.getIdes().getIdes());
        float thongke= es.getTongtien();
        float tongtien = tinhTongtien(khoanchi,tccu - khoanchi.getTongchi());
        if(ct==null){
            throw new RuntimeException("Không tìm thấy id của chi tiết khoản chi: " + khoanchi.getIdctchi().getIdctchi());
        }

        if(khoanchi.getTongchi()>tccu){
            thongke += khoanchi.getTongchi() - tccu;
        }else{
            thongke -= tccu - khoanchi.getTongchi(); 
        }
        es.setTongtien(thongke);
        esservice.saveES(es);

        ct.setTongchi(khoanchi.getTongchi());
        ct.setTongtien(tongtien);
        ctrepository.save(ct);

        khoanchi.setIduser(user);
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
        User user = uservice.getUserbyID(1);
        Khoanchi khoanchi = getKhoanchibyID(id);

        ExpenseStatistic es = esservice.getESbyID(user.getIdes().getIdes());
        es.setTongtien(es.getTongtien()-khoanchi.getTongchi());
        esservice.saveES(es);

        khoanchi.setTongchi(0-khoanchi.getTongchi());
        tinhTongtien(khoanchi, 0);

        ctrepository.deleteById(khoanchi.getIdctchi().getIdctchi());
        this.repository.deleteById(id);
    }

    public Page<Khoanchi> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : 
            Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return this.repository.findAll(pageable);
    }

    public float tinhTongtien(Khoanchi khoanchi, float du){
        Tongtien tt = uservice.getUserbyID(1).getIdtongtien();
        tt.setTongtien(tt.getTongtien()-khoanchi.getTongchi()+du);
        if(tt.getTongtien()<0){
            tt.setTongtien(0);
            tt.setNo(0-tt.getTongtien());
        }
        ttrepository.save(tt);
        return tt.getTongtien();
    }

    public Page<Khoanchi> getPage(Pageable pageable){
        return this.repository.findAll(pageable);
    }

    public List<Khoanchi> findAll(String keyword){
        if(keyword!=null){
            return repository.findAllKC(keyword);
        }
        return Collections.emptyList();
    }
}
