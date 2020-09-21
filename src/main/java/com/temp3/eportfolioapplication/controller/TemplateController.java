package com.temp3.eportfolioapplication.controller;


import com.temp3.eportfolioapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class TemplateController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/choose")
    public String showTemplates(Model model){
        return "choose";
    }

}
