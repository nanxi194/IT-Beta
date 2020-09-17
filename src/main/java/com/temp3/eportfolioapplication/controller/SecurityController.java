package com.temp3.eportfolioapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
    @RequestMapping("/")
    private String home(){
        return "home";
    }
}
