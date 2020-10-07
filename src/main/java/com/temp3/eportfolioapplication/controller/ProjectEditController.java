package com.temp3.eportfolioapplication.controller;

import com.temp3.eportfolioapplication.model.DatabaseFile;
import com.temp3.eportfolioapplication.model.Project;
import com.temp3.eportfolioapplication.repository.DatabaseFileRepository;
import com.temp3.eportfolioapplication.repository.ProjectRepository;
import com.temp3.eportfolioapplication.service.FileStorageService;
import com.temp3.eportfolioapplication.service.ProjectEditService;
import com.temp3.eportfolioapplication.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Controller
public class ProjectEditController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    DatabaseFileRepository databaseFileRepository;

    @Autowired
    ProjectEditService projectEditService;

    @Autowired
    FileStorageService fileService;

    @Autowired
    ProjectService projectService;

    List<String> IMAGE_TYPE = new ArrayList<>(Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/gif"));

    List<String> AUDIO_TYPE = new ArrayList<>(Arrays.asList("audio/mpeg", "audio/mp4"));

    List<String> VIDEO_TYPE = new ArrayList<>(Arrays.asList("video/mp4", "application/mp4"));

    List<String> DOC_TYPE = new ArrayList<>(Arrays.asList("application/pdf"));

    @GetMapping("/projects/edit/{id}")
    public String showProjectEdit(@PathVariable String id, Principal principal, Model model){

        Project project = projectRepository.findById(Long.parseLong(id));

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

        model.addAttribute("fileIDs", fileList);
        model.addAttribute("typeList", typeList);
        model.addAttribute("descriptions", descriptionList);
        model.addAttribute("projectName", projectName);
        model.addAttribute("projectID", id);

        System.out.println(typeList.toString());

        return "projectEdit";
    }

    @PostMapping("/projects/edit/{id}")
    public String editProject(@PathVariable String id,
                              @RequestParam("files") MultipartFile files[],
                              @RequestParam(name = "descriptions", required = false) String descriptions[],
                              @RequestParam("display") MultipartFile display,
                              @RequestParam("projectname") String projectName,
                              @RequestParam(value = "newFiles", required = false) MultipartFile newFiles[],
                              @RequestParam(value = "newDescription", required = false) String newDescription[],
                              @RequestParam(value = "delete", required = false) String deleteId,
                              Principal principal) throws IOException {

        Project project = projectRepository.findById(Long.parseLong(id));

        if (deleteId != null){
            DatabaseFile file = databaseFileRepository.findById(Long.parseLong(deleteId));
            databaseFileRepository.delete(file);

            Iterable<DatabaseFile> oldFiles = databaseFileRepository.findAllByProject(project);
            int i = 0;
            for (DatabaseFile fileCount:oldFiles){
                i++;
            }

            if(i == 0){
                return "redirect:/user/" +  principal.getName();
            }
            return "redirect:/projects/edit/" + id;
        }

        Iterable<DatabaseFile> oldFiles = databaseFileRepository.findAllByProject(project);

        List<DatabaseFile> fileList = new ArrayList<>();

        for(DatabaseFile file:oldFiles){
            fileList.add(file);
        }

        for(int i = 0; i < fileList.size(); i++){
            projectEditService.changeFile(fileList.get(i), files[i], descriptions[i]);
        }

        if(display.getSize() != 0){
            project.setDisplay(display.getBytes());
        }

        project.setProjectName(projectName);

        projectRepository.save(project);

        if(newFiles.length != 0 && newFiles[0].getSize() != 0){
            Set<DatabaseFile> fileSet = new HashSet<>();

            DatabaseFile currFile;

            for (int i = 0; i < newFiles.length; i++){
                currFile = fileService.storeFile(newFiles[i], principal.getName(), newDescription[i]);

                fileSet.add(currFile);
            }

            projectService.addToProject(fileSet, id);
        }


        return "redirect:/projects/" +  principal.getName() + "/" + id;
    }
}
