package com.temp3.eportfolioapplication.controller;

import com.temp3.eportfolioapplication.model.DatabaseFile;
import com.temp3.eportfolioapplication.model.User;
import com.temp3.eportfolioapplication.repository.DatabaseFileRepository;
import com.temp3.eportfolioapplication.repository.UserRepository;
import com.temp3.eportfolioapplication.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.*;
import java.util.List;

@Controller
public class UserProfileController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DatabaseFileRepository databaseFileRepository;

    @Autowired
    ImageService imageService;


    List<String> IMAGE_TYPE = new ArrayList<>(Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/gif"));

    List<String> AUDIO_TYPE = new ArrayList<>(Arrays.asList("audio/mpeg", "audio/mp4"));

    List<String> VIDEO_TYPE = new ArrayList<>(Arrays.asList("video/mp4", "application/mp4"));

    List<String> DOC_TYPE = new ArrayList<>(Arrays.asList("application/pdf"));



    @GetMapping("/user/{username}")
    public String getProfilePage(@PathVariable String username, Model model, Principal principal){

        User currUser = userRepository.findByUsername(username);

        if(currUser == null){
            return "error";
        }

        if(principal == null || !principal.getName().equals(username)){
            if(currUser.getPrivacy().equals("private")){
                return "private";
            }
        }

        List<Long> imageList = new ArrayList<>();

        Iterable<DatabaseFile> files = databaseFileRepository.findAllByUser(username);

        for(DatabaseFile file:files){
            if(IMAGE_TYPE.contains(file.getFileType())){
                imageList.add(file.getId());
            }
        }

        List<Long> audioList = new ArrayList<>();

        for(DatabaseFile file:files){
            if(AUDIO_TYPE.contains(file.getFileType())){
                audioList.add(file.getId());
            }
        }

        List<Long> videoList = new ArrayList<>();

        for(DatabaseFile file:files){
            if(VIDEO_TYPE.contains(file.getFileType())){
                videoList.add(file.getId());
            }
        }

        Map<Long, String> docMap = new HashMap<>();

        for(DatabaseFile file:files){
            if(DOC_TYPE.contains(file.getFileType())){
                docMap.put(file.getId(), file.getFileName());
            }
        }

        model.addAttribute("imageIDs", imageList);

        model.addAttribute("audioIDs", audioList);

        model.addAttribute("videoIDs", videoList);

        model.addAttribute("docs", docMap);

        return "profile";
    }

}