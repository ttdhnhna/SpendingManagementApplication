package com.project.SpendingManagementApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.SpendingManagementApplication.entity.Khoanchi;
import com.project.SpendingManagementApplication.service.KhoanchiService;

public class KhoanchiController {
    @Autowired
    KhoanchiService service;

    @GetMapping("/cackhoanchi")
    public List<Khoanchi> getKhoanchi(Model model){
        // List<Khoanchi> ListKhoanchi = service.getKhoanchi();
        // model.addAttribute("ListKhoanchi", ListKhoanchi);
        // return "homepage";
        return service.getKhoanchi();
    }

    @PostMapping("/themkhoanchi")
    public void saveKhoanchi(Khoanchi khoanchi){
        service.saveKhoanchi(khoanchi);
    }

    @GetMapping("/timkhoanchi/{id}")
    public Khoanchi getKhoanchiById(@PathVariable long id) {
        return service.getKhoanchibyID(id);
    }

    @DeleteMapping("/xoakhoanchi/{id}")
    public void deleteKhoanchiById(@PathVariable long id) {
        service.deleteKhoanchibyID(id);
    }
}
