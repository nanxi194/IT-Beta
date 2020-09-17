package com.temp3.eportfolioapplication.controller;

import com.temp3.eportfolioapplication.service.AudioService;
import com.temp3.eportfolioapplication.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class VideoController {

    @Autowired
    VideoService videoService;

    @GetMapping("/video/{username}/{id}")
    public void getUserVideo(@PathVariable String username, @PathVariable String id, HttpServletResponse response) throws IOException {
        videoService.writeVideoToResponse(Long.parseLong(id), response);
    }

}
