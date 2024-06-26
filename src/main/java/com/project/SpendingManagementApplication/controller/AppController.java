package com.project.SpendingManagementApplication.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.SpendingManagementApplication.entity.Khoanchi;
import com.project.SpendingManagementApplication.entity.Khoanthu;
import com.project.SpendingManagementApplication.entity.Tongtien;
import com.project.SpendingManagementApplication.entity.User;
import com.project.SpendingManagementApplication.entity.UserDto;
import com.project.SpendingManagementApplication.service.KhoanchiService;
import com.project.SpendingManagementApplication.service.KhoanthuService;
import com.project.SpendingManagementApplication.service.TongtienService;
import com.project.SpendingManagementApplication.service.UserService;

@Controller
public class AppController {
    @Autowired
    KhoanchiService kcservice;
    @Autowired
    KhoanthuService ktservice;
    @Autowired
    TongtienService ttservice;
    @Autowired
    UserService uservice;

    @GetMapping("/")
    public String getAll(Model model){
        List<Khoanchi> ListKhoanchi = kcservice.getKhoanchi();
        List<Khoanthu> ListKhoanthu = ktservice.getKhoanthu();
        List<Object> combindedList = new ArrayList<>();
        combindedList.addAll(ListKhoanchi);
        combindedList.addAll(ListKhoanthu);
        combindedList.sort((o1, o2) -> {
            Date date1 = (o1 instanceof Khoanchi) ? ((Khoanchi) o1).getNgaychi() : ((Khoanthu) o1).getNgaythu();
            Date date2 = (o2 instanceof Khoanchi) ? ((Khoanchi) o2).getNgaychi() : ((Khoanthu) o2).getNgaythu();
            return date1.compareTo(date2);
        });
        model.addAttribute("combindedList", combindedList);
        Tongtien tt = ttservice.getTongtienbyID(uservice.getUserbyID(1).getIdtongtien().getIdtongtien());
        model.addAttribute("tt", tt);
        return "homepage";
    }

    @GetMapping("/userpage")
    public String userDetails(Model model){
        User user = uservice.getUserbyID(1);
        model.addAttribute("user", user);
        Tongtien tt = ttservice.getTongtienbyID(uservice.getUserbyID(1).getIdtongtien().getIdtongtien());
        model.addAttribute("tt", tt);
        return "accountpage";
    }

    @GetMapping("/updateAccount/{id}")
    public String updateUser(@PathVariable(value = "id") long id, Model model){
        User user = uservice.getUserbyID(id);
        model.addAttribute("user", user);
        return "updateaccount";
    }

    @PostMapping("/updateAccount")
    public String updateAccount(@ModelAttribute("user") UserDto userDto, Model model){
        User user = uservice.getUserbyID(userDto.getIduser());
        uservice.updateUser(user, userDto);
        return "redirect:/userpage";
    }
}
