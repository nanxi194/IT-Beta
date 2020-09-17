package com.temp3.eportfolioapplication.service;

import com.temp3.eportfolioapplication.model.DatabaseFile;
import com.temp3.eportfolioapplication.repository.DatabaseFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

@Service
public class ImageService {

    @Autowired
    DatabaseFileRepository databaseFileRepository;

    public void writeImageToResponse(long id, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.setHeader("Cache-Control", "max-age=2628000");

        DatabaseFile imageFile = databaseFileRepository.findById(id);

        byte[] imageBytes = imageFile.getData();

        try (OutputStream out = response.getOutputStream()) {
            out.write(imageBytes);
        }

    }
}
