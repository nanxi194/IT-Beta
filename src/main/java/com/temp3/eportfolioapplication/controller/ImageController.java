package com.temp3.eportfolioapplication.controller;

import com.temp3.eportfolioapplication.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ImageController {

    @Autowired
    ImageService imageService;

    @GetMapping("/image/{username}/{id}")
    public void getUserImage(@PathVariable String username, @PathVariable String id, HttpServletResponse response) throws IOException {
        imageService.writeImageToResponse(Long.parseLong(id), response);
    }

    @GetMapping("/user/{username}/picture")
    public void getProfilePicture(@PathVariable String username, HttpServletResponse response) throws IOException {
        imageService.writeProfilePicToResponse(username, response);
    }
}
