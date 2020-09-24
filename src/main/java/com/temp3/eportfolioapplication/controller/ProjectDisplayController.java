package com.temp3.eportfolioapplication.controller;

import com.temp3.eportfolioapplication.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ProjectDisplayController {

    @Autowired
    ImageService imageService;

    @GetMapping("/display/projects/{username}/{id}")
    public void getUserImage(@PathVariable String username, @PathVariable String id, HttpServletResponse response) throws IOException {
        imageService.writeDisplayToResponse(Long.parseLong(id), response);
    }
}
