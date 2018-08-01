package com.intel.site.controller;

import com.intel.site.dto.Sertificate;
import com.intel.site.services.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    DbService dbService;

    @GetMapping(value = "rules")
    public String rules() {
        return "rules";
    }

    @GetMapping(value = "home")
    public String home() {
        return "home";
    }


    @GetMapping(value = "discount")
    public String discount() {
        for (Sertificate sert : dbService.getAllSertificates()) {
            System.out.println(sert.getName());
        }
        return "discount";
    }

    @GetMapping(value = "contacts")
    public String contacts() {
        dbService.addSertificate(new Sertificate(dbService.getNewId(), "new", "phone", "email", true));
        return "contacts";
    }

    @RequestMapping(value = "sertificate", method = RequestMethod.GET)
    public String sertificate() {
        return "sertificate";
    }

    @RequestMapping(value = "sertificate", method = RequestMethod.POST)
    public String newSertificate(@RequestParam String name, @RequestParam String phone, @RequestParam String email) {
        dbService.addSertificate(new Sertificate(dbService.getNewId(), name, phone, email));
        return "sertificate";
    }

    @GetMapping()
    public String main() {
        return "main";
    }

    @GetMapping(value = "admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "admin", method = RequestMethod.POST)
    public String admen(@RequestParam int id) {
        dbService.changeDone(id, true);
        return "admin";
    }

    @RequestMapping(value = "adminpage", method = RequestMethod.POST)
    public String adminPage(@RequestParam String admin, Model model) {
        if (admin.equals("vad")){
            List<Sertificate> list = dbService.getAllSertificates();
            model.addAttribute("list",list);
            System.out.println(list.get(0).getName());
            return "adminpage";
        }
        return "admin";
    }
}
