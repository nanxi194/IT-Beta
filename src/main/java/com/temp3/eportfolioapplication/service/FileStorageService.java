package com.temp3.eportfolioapplication.service;

import com.temp3.eportfolioapplication.model.DatabaseFile;
import com.temp3.eportfolioapplication.repository.DatabaseFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileStorageService {

    @Autowired
    private DatabaseFileRepository databaseFileRepository;


    public DatabaseFile storeFile(MultipartFile file, String username, String description) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        DatabaseFile databaseFile = new DatabaseFile(fileName, file.getContentType(), file.getBytes(), username, description);

        databaseFileRepository.save(databaseFile);

        return databaseFile;
    }
}
