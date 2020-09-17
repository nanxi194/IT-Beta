package com.temp3.eportfolioapplication.controller;

import com.temp3.eportfolioapplication.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AudioController {

    @Autowired
    AudioService audioService;

    @GetMapping("/audio/{username}/{id}")
    public void getUserAudio(@PathVariable String username, @PathVariable String id, HttpServletResponse response) throws IOException {
        audioService.writeAudioToResponse(Long.parseLong(id), response);
    }
}
