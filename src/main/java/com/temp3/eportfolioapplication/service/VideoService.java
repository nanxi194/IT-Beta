package com.temp3.eportfolioapplication.service;

import com.temp3.eportfolioapplication.model.DatabaseFile;
import com.temp3.eportfolioapplication.repository.DatabaseFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


@Service
public class VideoService {

    @Autowired
    DatabaseFileRepository databaseFileRepository;

    public void writeVideoToResponse(long id, HttpServletResponse response) throws IOException {
        response.setContentType("video/mp4, application/mp4");
        response.setHeader("Cache-Control", "max-age=2628000");

        DatabaseFile videoFile = databaseFileRepository.findById(id);

        byte[] videoBytes = videoFile.getData();

        try (OutputStream out = response.getOutputStream()) {
            out.write(videoBytes);
        }

    }
}
