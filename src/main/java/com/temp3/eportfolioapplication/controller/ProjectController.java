package com.temp3.eportfolioapplication.controller;

import com.temp3.eportfolioapplication.model.DatabaseFile;
import com.temp3.eportfolioapplication.model.Project;
import com.temp3.eportfolioapplication.model.User;
import com.temp3.eportfolioapplication.repository.DatabaseFileRepository;
import com.temp3.eportfolioapplication.repository.ProjectRepository;
import com.temp3.eportfolioapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    DatabaseFileRepository databaseFileRepository;

    @Autowired
    UserRepository userRepository;

    List<String> IMAGE_TYPE = new ArrayList<>(Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/gif"));

    List<String> AUDIO_TYPE = new ArrayList<>(Arrays.asList("audio/mpeg", "audio/mp4"));

    List<String> VIDEO_TYPE = new ArrayList<>(Arrays.asList("video/mp4", "application/mp4"));

    List<String> DOC_TYPE = new ArrayList<>(Arrays.asList("application/pdf"));


    @GetMapping("/projects/{username}/{id}")
    public String displayProject(@PathVariable String username, @PathVariable String id, Model model,
                                 Principal principal){

        Project project = projectRepository.findById(Long.parseLong(id));

        User user = userRepository.findByUsername(username);

        Iterable<DatabaseFile> files = databaseFileRepository.findAllByProject(project);

        List<Long> fileList = new ArrayList<>();

        List<String> descriptionList = new ArrayList<>();

        List<String> typeList = new ArrayList<>();

        String projectName = project.getProjectName();


        for(DatabaseFile file:files){
            fileList.add(file.getId());
            descriptionList.add(file.getDescription());
            if (IMAGE_TYPE.contains(file.getFileType())){
                typeList.add("image");
            }
            if (AUDIO_TYPE.contains(file.getFileType())){
                typeList.add("audio");
            }
            if (VIDEO_TYPE.contains(file.getFileType())){
                typeList.add("video");
            }
            if (DOC_TYPE.contains(file.getFileType())){
                typeList.add("doc");
            }
        }

        boolean showEdit = false;

        if(principal != null && principal.getName().equals(username)){
            showEdit = true;
        }

        model.addAttribute("fileIDs", fileList);
        model.addAttribute("typeList", typeList);
        model.addAttribute("descriptions", descriptionList);
        model.addAttribute("projectName", projectName);
        model.addAttribute("projectID", id);
        model.addAttribute("showEdit", showEdit);
        model.addAttribute("username", username);

        if(principal == null || !principal.getName().equals(username)){
            if(user.getPrivacy().equals("private")){
                return "redirect:/private";
            }
        }

        return "project";
    }

}
