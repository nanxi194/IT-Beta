package com.temp3.eportfolioapplication.service;

import com.temp3.eportfolioapplication.model.DatabaseFile;
import com.temp3.eportfolioapplication.repository.DatabaseFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProjectEditService {

    @Autowired
    DatabaseFileRepository databaseFileRepository;

    public void changeDescription(DatabaseFile databaseFile, String description){

        databaseFile.setDescription(description);
    }

    public void changeFile(DatabaseFile databaseFile, MultipartFile file, String description) throws IOException {

        if(file.getSize() != 0){
            databaseFile.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
            databaseFile.setData(file.getBytes());
            databaseFile.setFileType(file.getContentType());
        }

        databaseFile.setDescription(description);

        databaseFileRepository.save(databaseFile);
    }
}
