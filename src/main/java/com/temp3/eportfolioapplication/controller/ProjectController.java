package com.temp3.eportfolioapplication.controller;

import com.temp3.eportfolioapplication.model.DatabaseFile;
import com.temp3.eportfolioapplication.model.Project;
import com.temp3.eportfolioapplication.repository.DatabaseFileRepository;
import com.temp3.eportfolioapplication.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    DatabaseFileRepository databaseFileRepository;


    @GetMapping("/projects/{username}/{id}")
    public String displayProject(@PathVariable String username, @PathVariable String id, Model model){

        Project project = projectRepository.findById(Long.parseLong(id));

        Iterable<DatabaseFile> files = databaseFileRepository.findAllByProject(project);

        List<Long> imageList = new ArrayList<>();

        List<String> descriptionList = new ArrayList<>();

        for(DatabaseFile file:files){
            imageList.add(file.getId());
            descriptionList.add(file.getDescription());
        }

        model.addAttribute("imageIDs", imageList);
        model.addAttribute("descriptions", descriptionList);

        return "project";
    }

}
