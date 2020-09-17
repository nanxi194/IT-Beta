package com.temp3.eportfolioapplication.controller;

import com.temp3.eportfolioapplication.exception.FileTooLargeException;
import com.temp3.eportfolioapplication.service.FileService;
import com.temp3.eportfolioapplication.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class FileController {

    @Autowired
    FileStorageService fileService;

    private List<String> files = new ArrayList<>();

    @GetMapping("/upload")
    public String displayUploadPage(Model model) {
        model.addAttribute("files", files);
        return "upload";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, Principal principal, RedirectAttributes redirectAttributes) throws IOException, FileTooLargeException {

        if(file.getSize() > 5242880){
            throw new FileTooLargeException("");
        }

        fileService.storeFile(file, principal.getName());

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        files.add(file.getOriginalFilename());

        return "redirect:/upload";
    }

    @ExceptionHandler(value = FileTooLargeException.class)
    public String handleExceededSize(RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("message",
                "File size exceeded 5MB. Please reduce file size and upload again");

        return "redirect:/upload";

    }
}
