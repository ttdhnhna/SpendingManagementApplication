package com.project.SpendingManagementApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.SpendingManagementApplication.entity.CTKhoanchi;
import com.project.SpendingManagementApplication.entity.Khoanchi;
import com.project.SpendingManagementApplication.entity.Tongtien;
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
    UserService uservice;
    @Autowired 
    TongtienRepository ttrepository;

    public List<Khoanchi> getKhoanchi(){
        return repository.findAll();
    }

    public void saveKhoanchi(Khoanchi khoanchi, String ghichu, String theloai){
        Tongtien tt = uservice.getUserbyID(1).getIdtongtien();

        CTKhoanchi ct=new CTKhoanchi();
        ct.setGhichu(ghichu);
        ct.setTheloai(theloai);
        ct.setTongchi(khoanchi.getTongchi());

        CTKhoanchi savedCT = ctrepository.save(ct);

        tt.setTongtien(tt.getTongtien()-khoanchi.getTongchi());
        if(tt.getTongtien()<0){
            tt.setTongtien(0);
            tt.setNo(0-tt.getTongtien());
        }
        ttrepository.save(tt);

        khoanchi.setIdctchi(savedCT);
        khoanchi.setIduser(uservice.getUserbyID(1));
        this.repository.save(khoanchi);

        savedCT.setIdkhoanchi(khoanchi);
        ctrepository.save(savedCT);
    }

    public void updateKhoanchi(Khoanchi khoanchi, CTKhoanchi ct){
        Tongtien tt = uservice.getUserbyID(1).getIdtongtien();
        if(ct==null){
            throw new RuntimeException("Không tìm thấy id của chi tiết khoản chi: " + khoanchi.getIdctchi().getIdctchi());
        }
        tt.setTongtien(tt.getTongtien()-khoanchi.getTongchi());
        if(tt.getTongtien()<0){
            tt.setTongtien(0);
            tt.setNo(0-tt.getTongtien());
        }
        ttrepository.save(tt);

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

    public Page<Khoanchi> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : 
            Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return this.repository.findAll(pageable);
    }
}
