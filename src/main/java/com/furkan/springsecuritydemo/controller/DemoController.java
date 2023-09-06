package com.furkan.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String showHome(){
        return "home";
    }

    @GetMapping("/leaders")
    public String leadersPage() {return "leaders";}

    @GetMapping("/systems")
    public String systemsPage() {return "systems";}

    @GetMapping("/access-denied")
    public String accessDeniedPage() {return "access-denied";}

}
