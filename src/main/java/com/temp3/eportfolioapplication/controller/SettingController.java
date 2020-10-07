package com.temp3.eportfolioapplication.controller;

import com.temp3.eportfolioapplication.model.User;
import com.temp3.eportfolioapplication.model.UserSettings;
import com.temp3.eportfolioapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class SettingController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/settings")
    public String showSettings(Model model){
        model.addAttribute("userSettings", new UserSettings());
        return "settings";
    }

    @PostMapping("/settings")
    public String changeSettings(@ModelAttribute UserSettings userSettings, Principal principal, RedirectAttributes redirectAttributes){
        User currUser = userRepository.findByUsername(principal.getName());

        currUser.setPrivacy(userSettings.getPrivacy());

        userRepository.save(currUser);

        redirectAttributes.addFlashAttribute("message",
                "Your settings have been changed");

        System.out.println(currUser.getPrivacy());

        return "redirect:/settings";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("current") String currentPassword,
                                 @RequestParam("new") String newPassword,
                                 @RequestParam("retype") String retypePassword,
                                 Principal principal,
                                 RedirectAttributes redirectAttributes){

        User user = userRepository.findByUsername(principal.getName());

        if (!user.getPassword().equals(currentPassword)){
            redirectAttributes.addFlashAttribute("error", "Current password is wrong");

            return "redirect:/settings";
        }

        if (!newPassword.equals(retypePassword)){
            redirectAttributes.addFlashAttribute("error", "Retype password is wrong");

            return "redirect:/settings";
        }

        user.setPassword(newPassword);

        userRepository.save(user);

        return "redirect:/settings";
    }

}
