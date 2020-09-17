package com.temp3.eportfolioapplication.service;

import com.temp3.eportfolioapplication.model.DatabaseFile;
import com.temp3.eportfolioapplication.repository.DatabaseFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class AudioService {

    @Autowired
    DatabaseFileRepository databaseFileRepository;

    public void writeAudioToResponse(long id, HttpServletResponse response) throws IOException {
        response.setContentType("audio/mpeg, audio/mp4");
        response.setHeader("Cache-Control", "max-age=2628000");

        DatabaseFile audioFile = databaseFileRepository.findById(id);

        byte[] audioBytes = audioFile.getData();

        try (OutputStream out = response.getOutputStream()) {
            out.write(audioBytes);
        }

    }
}
