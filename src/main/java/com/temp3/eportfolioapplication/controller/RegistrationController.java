package com.temp3.eportfolioapplication.controller;

import com.temp3.eportfolioapplication.model.User;
import com.temp3.eportfolioapplication.model.UserDto;
import com.temp3.eportfolioapplication.service.UserService;
import org.hibernate.annotations.CollectionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);

        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") UserDto userDto,
            HttpServletRequest request, Errors errors, RedirectAttributes redirectAttributes) {

        userService.registerNewUser(userDto);

        redirectAttributes.addFlashAttribute("message",
                "You have successfully registered. Please log in.");

        return new ModelAndView("redirect:/login");
    }


}
