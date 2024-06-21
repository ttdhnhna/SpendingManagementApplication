package com.project.SpendingManagementApplication.service;

import com.project.SpendingManagementApplication.entity.CTKhoanchi;
import com.project.SpendingManagementApplication.repository.CTKhoanchiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CTKhoanchiService {

    @Autowired
    CTKhoanchiRepository repository;

    public List<CTKhoanchi> getCTKhoanchi(){
        return repository.findAll();
    }

    public void saveCTKhoanchi(CTKhoanchi ctKhoanchi){
        this.repository.save(ctKhoanchi);
    }

    public CTKhoanchi getCTKhoanchibyID(long id){
        Optional<CTKhoanchi> optional = repository.findById(id);
        CTKhoanchi ctKhoanchi = null;
        if(optional.isPresent()){
            ctKhoanchi=optional.get();
        }else{
            throw new RuntimeException("Không tìm thấy ID chi tiêt khoản chi: "+id);
        }
        return ctKhoanchi;
    }

    public void deleteCTKhoanchibyID(long id){
        this.repository.deleteById(id);
    }
}
