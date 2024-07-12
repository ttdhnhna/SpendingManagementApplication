package com.project.SpendingManagementApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.SpendingManagementApplication.entity.Khoanthu;
import org.springframework.stereotype.Repository;

@Repository
public interface KhoanthuRepository extends JpaRepository<Khoanthu, Long>{
    @Query(value = "SELECT * FROM tbl_income i WHERE i.idkhoanthu LIKE %?1% "
    + "OR i.tongthu LIKE %?1%  "
    + "OR i.ngaythu LIKE %?1%  ", nativeQuery = true)
    public List<Khoanthu> findAllKT(String keyword);
}
