package com.project.SpendingManagementApplication.controller;

import java.util.List;

import com.project.SpendingManagementApplication.entity.ExpenseStatistic;
import com.project.SpendingManagementApplication.service.ExpenseStatisticService;
import com.project.SpendingManagementApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
    @Autowired
    ExpenseStatisticService esservice;
    @Autowired
    UserService uservice;

    @GetMapping("/expenses")
    public String getExpenses(Model model){
        return findPaginated(1, "idkhoanchi", "asc", model);
    }

    @PostMapping("/saveExpense")
    public String saveExpense(@ModelAttribute("khoanchi") Khoanchi khoanchi,
                              @RequestParam("ghichu") String ghichu,
                              @RequestParam("theloai") String theloai){
        service.saveKhoanchi(khoanchi, ghichu, theloai);
        return "redirect:/";
    }

    @PostMapping("/updateExpense")
    public String updateExpense(@ModelAttribute("khoanchi") Khoanchi khoanchi,
                              @ModelAttribute("ctkhoanchi") CTKhoanchi ct){
        service.updateKhoanchi(khoanchi, ct);
        return "redirect:/";
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
        return "redirect:/";
    }

    @GetMapping("/pageKC/{pageKCNo}")
    public String findPaginated(@PathVariable(value = "pageKCNo") int pageNo,
        @RequestParam("sortField") String sortField,
        @RequestParam("sortDir") String sortDir, Model model){
        ExpenseStatistic es = esservice.getESbyID(uservice.getUserbyID(1).getIdes().getIdes());
        int pageSize = 10;

        Page<Khoanchi> page = service.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Khoanchi> ListKhoanchi = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("ListKhoanchi", ListKhoanchi);
        model.addAttribute("es", es);
        return "expenses";
    }

    @GetMapping("/expenseDetail/{id}")
    public String expenseDetail(@PathVariable(value = "id") long id, Model model){
        Khoanchi khoanchi = service.getKhoanchibyID(id);
        model.addAttribute("khoanchi", khoanchi);
        return "expensedetail";
    }

    @GetMapping("/findkc")
    public String findAllKC(Model model, @Param("keyword")String keyword){
        List<Khoanchi> ListKhoanchi = service.findAll(keyword);
        model.addAttribute("ListKhoanchi", ListKhoanchi);
        ExpenseStatistic es = esservice.getESbyID(uservice.getUserbyID(1).getIdes().getIdes());
        model.addAttribute("es", es);
        if(ListKhoanchi.isEmpty()){
            model.addAttribute("errorMess", "Không tìm thấy khoản chi");
        }
        return "expenses";
    }
}
