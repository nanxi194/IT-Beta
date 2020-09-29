package com.temp3.eportfolioapplication.controller;

import com.temp3.eportfolioapplication.model.DatabaseFile;
import com.temp3.eportfolioapplication.model.Project;
import com.temp3.eportfolioapplication.repository.DatabaseFileRepository;
import com.temp3.eportfolioapplication.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectEditController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    DatabaseFileRepository databaseFileRepository;

    @GetMapping("/projects/edit/{id}")
    public String showProjectEdit(@PathVariable String id, Principal principal){

        Project project = projectRepository.findById(Long.parseLong(id));

        if(principal == null || !project.getUsername().equals(principal.getName())){
            return "private";
        }

        Iterable<DatabaseFile> files = databaseFileRepository.findAllByProject(project);

        List<DatabaseFile> fileList = new ArrayList<>();



        return "projectEdit";
    }
}
