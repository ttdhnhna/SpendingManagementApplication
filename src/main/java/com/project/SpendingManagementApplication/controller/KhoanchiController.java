package com.project.SpendingManagementApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.SpendingManagementApplication.entity.CTKhoanchi;
import com.project.SpendingManagementApplication.entity.Khoanchi;
import com.project.SpendingManagementApplication.service.CTKhoanchiService;
import com.project.SpendingManagementApplication.service.KhoanchiService;

@Controller
public class KhoanchiController {
    @Autowired
    KhoanchiService service;
    @Autowired
    CTKhoanchiService ctservice;

    @GetMapping("/expenses")
    public String getExpenses(Model model){
        List<Khoanchi> ListKhoanchi = service.getKhoanchi();
        model.addAttribute("ListKhoanchi", ListKhoanchi);
        return "expenses";
    }

    @PostMapping("/saveExpense")
    public String saveExpense(@ModelAttribute("khoanchi") Khoanchi khoanchi,
                              @RequestParam("ghichu") String ghichu,
                              @RequestParam("theloai") String theloai){
        service.saveKhoanchi(khoanchi, ghichu, theloai);
        return "redirect:/expenses";
    }

    @PostMapping("/updateExpense")
    public String updateExpense(@ModelAttribute("khoanchi") Khoanchi khoanchi,
                              @ModelAttribute("ctkhoanchi") CTKhoanchi ct){
        service.updateKhoanchi(khoanchi, ct);
        return "redirect:/expenses";
    } 

    @GetMapping("/addExpense")
    public String addExpense(Model model){
        Khoanchi khoanchi = new Khoanchi();
        model.addAttribute("khoanchi", khoanchi);
        return "newexpense";
    }

    @GetMapping("/updateExpense/{id}")
    public String updateExpense(@PathVariable(value = "id") long id, Model model) {
        Khoanchi khoanchi = service.getKhoanchibyID(id);
        model.addAttribute("khoanchi", khoanchi);
        CTKhoanchi ctKhoanchi = ctservice.getCTKhoanchibyID(khoanchi.getIdctchi().getIdctchi());
        model.addAttribute("ctkhoanchi", ctKhoanchi);
        return "updateexpense";
    }

    @GetMapping("/deleteExpense/{id}")
    public String deleteExpenseById(@PathVariable(value = "id") long id) {
        this.service.deleteKhoanchibyID(id);
        return "redirect:/expenses";
    }
}
