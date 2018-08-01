package com.intell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "home")
    public String home(Model model){
        model.addAttribute("name","lol");
        return "home";
    }
}
