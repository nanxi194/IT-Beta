package com.temp3.eportfolioapplication.controller;

import com.temp3.eportfolioapplication.model.User;
import com.temp3.eportfolioapplication.model.UserDto;
import com.temp3.eportfolioapplication.model.UserInfo;
import com.temp3.eportfolioapplication.model.UserInfoDto;
import com.temp3.eportfolioapplication.repository.UserInfoRepository;
import com.temp3.eportfolioapplication.repository.UserRepository;
import com.temp3.eportfolioapplication.service.UserInfoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
public class ProfileInformationController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/profile/{username}/edit")
    public String showProfileEdit(@PathVariable String username, Model model, Principal principal){

        if(principal == null || !principal.getName().equals(username)){
            return "denied";
        }

        UserInfo userInfo = userRepository.findByUsername(username).getUserInfo();

        model.addAttribute("prevInfo", userInfo);
        model.addAttribute("userInfo", new UserInfoDto());

        return "infoEdit";
    }

    @SneakyThrows
    @PostMapping("/profile/{username}/edit")
    public String changeInfo(@RequestParam("infos") String[] infos,
                             @RequestParam("picture") MultipartFile picture,
                             @PathVariable String username, Principal principal){

        if(principal == null || !principal.getName().equals(username)){
            return "private";
        }

        UserInfoDto userInfoDto;

        if(picture.getSize() != 0){
            byte[] pictureByte = picture.getBytes();
            userInfoDto = new UserInfoDto(infos, pictureByte);
        }
        else{
            userInfoDto = new UserInfoDto(infos, null);
        }


        User currUser = userRepository.findByUsername(username);

        UserInfo userInfo = currUser.getUserInfo();

        userInfo = userInfoService.saveInfoChange(userInfo, userInfoDto);

        userInfoRepository.save(userInfo);

        currUser.setUserInfo(userInfo);

        userRepository.save(currUser);

        return "redirect:/user/" + username;
    }


}
