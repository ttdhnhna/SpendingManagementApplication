package com.project.SpendingManagementApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.SpendingManagementApplication.entity.Khoanchi;
import org.springframework.stereotype.Repository;

@Repository
public interface KhoanchiRepository extends JpaRepository<Khoanchi, Long>{
    @Query(value = "SELECT * FROM tbl_expense e WHERE e.idkhoanchi LIKE %?1% "
    + "OR e.tongchi LIKE %?1%  "
    + "OR e.ngaychi LIKE %?1%  ", nativeQuery = true)
    public List<Khoanchi> findAllKC(String keyword);
}
