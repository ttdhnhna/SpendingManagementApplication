package com.project.SpendingManagementApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.SpendingManagementApplication.entity.Khoanthu;
import com.project.SpendingManagementApplication.service.KhoanthuService;

@Controller
public class KhoanthuController {
    @Autowired
    KhoanthuService service;

    @GetMapping("/incomes")
    public String getIncomes(Model model){
        List<Khoanthu> ListKhoanthu = service.getKhoanthu();
        model.addAttribute("ListKhoanthu", ListKhoanthu);
        return "incomes";
    }

    @PostMapping("/saveIncome")
    public String saveIncome(@ModelAttribute("khoanthu") Khoanthu khoanthu){
        service.saveKhoanthu(khoanthu);
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
        return "updateincome";
    }

    @GetMapping("/deleteIncome/{id}")
    public String deleteIncomeByID(@PathVariable(value = "id") long id){
        this.service.deleteKhoanthubyID(id);
        return "redirect:/incomes";
    }
}
