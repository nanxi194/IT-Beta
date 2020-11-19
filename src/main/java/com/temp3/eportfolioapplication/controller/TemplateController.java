package com.temp3.eportfolioapplication.controller;


import com.temp3.eportfolioapplication.model.User;
import com.temp3.eportfolioapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;


@Controller
public class TemplateController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/choose")
    public String showTemplates(Model model){
        return "choose";
    }

    @PostMapping("/template/{templateID}")
    public String changeTemplate(Principal principal, @PathVariable String templateID) {
        User user = userRepository.findByUsername(principal.getName());

        user.setTemplate(Integer.parseInt(templateID));

        userRepository.save(user);

        return "redirect:/user/" + principal.getName();
    }
}
