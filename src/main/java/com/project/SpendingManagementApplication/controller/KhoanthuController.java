package com.project.SpendingManagementApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.SpendingManagementApplication.entity.CTKhoanthu;
import com.project.SpendingManagementApplication.entity.Khoanthu;
import com.project.SpendingManagementApplication.service.CTKhoanthuService;
import com.project.SpendingManagementApplication.service.KhoanthuService;

@Controller
public class KhoanthuController {
    @Autowired
    KhoanthuService service;
    @Autowired
    CTKhoanthuService ctservice;

    @GetMapping("/incomes")
    public String getIncomes(Model model){
        List<Khoanthu> ListKhoanthu = service.getKhoanthu();
        model.addAttribute("ListKhoanthu", ListKhoanthu);
        return "incomes";
    }

    @PostMapping("/saveIncome")
    public String saveIncome(@ModelAttribute("khoanthu") Khoanthu khoanthu,
                             @RequestParam("ghichu") String ghichu,
                             @RequestParam("theloai") String theloai){
        service.saveKhoanthu(khoanthu, ghichu, theloai);
        return "redirect:/incomes";
    }

    @PostMapping("/updateIncome")
    public String jupdateIncome(@ModelAttribute("khoanthu") Khoanthu khoanthu,
                             @ModelAttribute("ctkhoanthu") CTKhoanthu ct){
        service.updateKhoanthu(khoanthu, ct);
        return "redirect:/incomes";
    }

    @GetMapping("/addIncome")
    public String addIncome(Model model){
        Khoanthu khoanthu = new Khoanthu();
        model.addAttribute("khoanthu", khoanthu);
        return "newincome";
    }

    @GetMapping("/updateIncome/{id}")
    public String updateIncome(@PathVariable(value = "id") long id, Model model){
        Khoanthu khoanthu = service.getKhoanthubyID(id);
        model.addAttribute("khoanthu", khoanthu);
        CTKhoanthu ctKhoanthu = ctservice.getCTKhoanthubyID(khoanthu.getIdctthu().getIdctthu());
        model.addAttribute("ctkhoanthu", ctKhoanthu);
        return "updateincome";
    }

    @GetMapping("/deleteIncome/{id}")
    public String deleteIncomeByID(@PathVariable(value = "id") long id){
        this.service.deleteKhoanthubyID(id);
        return "redirect:/incomes";
    }
}
