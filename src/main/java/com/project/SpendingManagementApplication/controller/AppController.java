package com.project.SpendingManagementApplication.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.SpendingManagementApplication.entity.Khoanchi;
import com.project.SpendingManagementApplication.entity.Khoanthu;
import com.project.SpendingManagementApplication.service.KhoanchiService;
import com.project.SpendingManagementApplication.service.KhoanthuService;

@Controller
public class AppController {
    @Autowired
    KhoanchiService kcservice;
    @Autowired
    KhoanthuService ktservice;

    @GetMapping("/")
    public String getAll(Model model){
        List<Khoanchi> ListKhoanchi = kcservice.getKhoanchi();
        List<Khoanthu> ListKhoanthu = ktservice.getKhoanthu();
        List<Object> combindedList = new ArrayList<>();
        combindedList.add(ListKhoanchi);
        combindedList.add(ListKhoanthu);
        model.addAttribute("combindedList", combindedList);
        return "homepage";
    }
}