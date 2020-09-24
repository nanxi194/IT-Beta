package com.temp3.eportfolioapplication.controller;

import com.temp3.eportfolioapplication.model.DatabaseFile;
import com.temp3.eportfolioapplication.repository.DatabaseFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class FileEditController {

    @Autowired
    DatabaseFileRepository databaseFileRepository;

    List<String> IMAGE_TYPE = new ArrayList<>(Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/gif"));

    List<String> AUDIO_TYPE = new ArrayList<>(Arrays.asList("audio/mpeg", "audio/mp4"));

    List<String> VIDEO_TYPE = new ArrayList<>(Arrays.asList("video/mp4", "application/mp4"));

    List<String> DOC_TYPE = new ArrayList<>(Arrays.asList("application/pdf"));

    @GetMapping("/file/edit/{id}")
    public String showEditPage(@PathVariable String id, Model model){

        DatabaseFile databaseFile = databaseFileRepository.findById(Long.parseLong(id));

        String username = databaseFile.getUser();

        String fileType = databaseFile.getFileType();

        String src = null;

        String type = null;

        if(IMAGE_TYPE.contains(fileType)){
            src = "/image/" + username + "/" + id;
            type = "image";
        }
        else if(AUDIO_TYPE.contains(fileType)){
            src = "/audio/" + username + "/" + id;
            type = "audio";
        }
        else if(VIDEO_TYPE.contains(fileType)){
            src = "/video/" + username + "/" + id;
            type = "video";
        }
        else if(DOC_TYPE.contains(fileType)){
            src = "/document/" + username + "/" + id;
            type = "document";
        }

        model.addAttribute("src", src);
        model.addAttribute("type", type);

        return "fileEdit";

    }

    @PostMapping("/file/edit/{id}")
    public String deleteFile(@PathVariable String id, Principal principal){

        DatabaseFile databaseFile = databaseFileRepository.findById(Long.parseLong(id));

        databaseFileRepository.delete(databaseFile);

        return "redirect:/user/" + principal.getName();
    }

}
