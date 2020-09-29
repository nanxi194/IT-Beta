package com.temp3.eportfolioapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class SecurityController {
    @RequestMapping("/")
    private String home(Principal principal){

        if(principal != null){
            return "hello";
        }
        return "home";
    }

    @GetMapping("/hello")
    private String hello(){
        return "hello";
    }
}
