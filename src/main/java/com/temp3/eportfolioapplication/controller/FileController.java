package com.temp3.eportfolioapplication.controller;

import com.temp3.eportfolioapplication.exception.FileTooLargeException;
import com.temp3.eportfolioapplication.model.DatabaseFile;
import com.temp3.eportfolioapplication.repository.DatabaseFileRepository;
import com.temp3.eportfolioapplication.service.FileStorageService;
import com.temp3.eportfolioapplication.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class FileController {

    @Autowired
    FileStorageService fileService;

    @Autowired
    ProjectService projectService;

    @Autowired
    DatabaseFileRepository databaseFileRepository;

    private List<String> fileList = new ArrayList<>();

    private int fileCount = 2;

    @GetMapping("/upload")
    public String displayUploadPage(Model model) {
        model.addAttribute("files", fileList);
        model.addAttribute("fileCount", fileCount);
        return "upload";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, Principal principal, RedirectAttributes redirectAttributes) throws IOException, FileTooLargeException {

        if(file.getSize() > 5242880){
            throw new FileTooLargeException("");
        }

        fileService.storeFile(file, principal.getName(), "");

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        fileList.add(file.getOriginalFilename());

        return "redirect:/upload";
    }

    @PostMapping("/uploadMultipleFiles")
    public String uploadMultipleFile(@RequestParam("files") MultipartFile[] files,
                                     @RequestParam("display") MultipartFile display,
                                     @RequestParam("descriptions") String[] descriptions,
                                     @RequestParam("projectName") String projectName,
                                     Principal principal, RedirectAttributes redirectAttributes) throws IOException, FileTooLargeException {

        Set<DatabaseFile> fileSet = new HashSet<>();

        DatabaseFile currFile;

        for(int i = 0; i < files.length; i++){
            if(files[i].getSize() > 5242880){
                throw new FileTooLargeException("");
            }
            if(files[i].getSize() != 0){
                if (descriptions.length == 0){
                    currFile = fileService.storeFile(files[i], principal.getName(), "");
                }
                else{
                    currFile = fileService.storeFile(files[i], principal.getName(), descriptions[i]);
                }


                fileSet.add(currFile);

                fileList.add(files[i].getOriginalFilename());
            }

        }

        if(display.getSize() == 0){
            projectService.storeProject(fileSet, principal.getName(), projectName);
        }
        else{
            projectService.storeProject(fileSet, principal.getName(), display.getBytes(), projectName);
        }


        return "redirect:/upload";
    }

    @GetMapping(value="/addrow")
    public String addRow(Model model) {
        fileCount++;
        System.out.println("i did something");
        model.addAttribute("fileCount", fileCount);
        return "upload";
    }

    @ExceptionHandler(value = FileTooLargeException.class)
    public String handleExceededSize(RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("message",
                "File size exceeded 5MB. Please reduce file size and upload again");

        return "redirect:/upload";

    }
}
