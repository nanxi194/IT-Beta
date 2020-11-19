package com.temp3.eportfolioapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivacyController {

    @GetMapping("/private")
    public String showPrivate (){
        return "private";
    }

    @GetMapping("/denied")
    public String showDenied(){
        return "denied";
    }
}
