package com.project.SpendingManagementApplication.controller;

import java.util.List;

import com.project.SpendingManagementApplication.entity.IncomeStatistic;
import com.project.SpendingManagementApplication.service.IncomeStatisticService;
import com.project.SpendingManagementApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
    @Autowired
    IncomeStatisticService isservice;
    @Autowired
    UserService uservice;

    @GetMapping("/incomes")
    public String getIncomes(Model model){
        return findPaginated(1, "idkhoanthu", "asc", model);
    }

    @PostMapping("/saveIncome")
    public String saveIncome(@ModelAttribute("khoanthu") Khoanthu khoanthu,
                             @RequestParam("ghichu") String ghichu,
                             @RequestParam("theloai") String theloai){
        service.saveKhoanthu(khoanthu, ghichu, theloai);
        return "redirect:/";
    }

    @PostMapping("/updateIncome")
    public String jupdateIncome(@ModelAttribute("khoanthu") Khoanthu khoanthu,
                             @ModelAttribute("ctkhoanthu") CTKhoanthu ct){
        service.updateKhoanthu(khoanthu, ct);
        return "redirect:/";
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
        return "redirect:/";
    }

    @GetMapping("/pageKT/{pageKTNo}")
    public String findPaginated(@PathVariable(value = "pageKTNo") int pageNo, 
        @RequestParam("sortField") String sortField,
        @RequestParam("sortDir") String sortDir, Model model){
        IncomeStatistic is = isservice.getISbyID(uservice.getUserbyID(1).getIdes().getIdes());
        int pageSize = 10;

        Page<Khoanthu> page = service.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Khoanthu> ListKhoanthu = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("ListKhoanthu", ListKhoanthu);
        model.addAttribute("is", is);
        return "incomes";
    }
    @GetMapping("/incomeDetail/{id}")
    public String expenseDetail(@PathVariable(value = "id") long id, Model model){
        Khoanthu khoanthu = service.getKhoanthubyID(id);
        model.addAttribute("khoanthu", khoanthu);
        return "incomedetail";
    }

    @GetMapping("/findkt")
    public String findAllKC(Model model, @Param("keyword")String keyword){
        List<Khoanthu> ListKhoanthu = service.findAll(keyword);
        model.addAttribute("ListKhoanthu", ListKhoanthu);
        if(ListKhoanthu.isEmpty()){
            model.addAttribute("errorMess", "Không tìm thấy khoản thu");
        }
        return "income";
    }
}
