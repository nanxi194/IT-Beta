package com.temp3.eportfolioapplication.service;

import com.temp3.eportfolioapplication.model.DatabaseFile;
import com.temp3.eportfolioapplication.repository.DatabaseFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class DocumentService {

    @Autowired
    DatabaseFileRepository databaseFileRepository;

    public void writeDocumentToResponse(long id, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Cache-Control", "max-age=2628000");

        DatabaseFile documentFile = databaseFileRepository.findById(id);

        byte[] documentBytes = documentFile.getData();

        try (OutputStream out = response.getOutputStream()) {
            out.write(documentBytes);
        }

    }
}

