package com.temp3.eportfolioapplication.service;

import com.temp3.eportfolioapplication.model.DatabaseFile;
import com.temp3.eportfolioapplication.model.Project;
import com.temp3.eportfolioapplication.repository.DatabaseFileRepository;
import com.temp3.eportfolioapplication.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public void storeProject(Set<DatabaseFile> files, String username) throws IOException {
        Project project = new Project(files, username);

        for(DatabaseFile file:files){
            file.setProject(project);
        }

        projectRepository.save(project);
    }

    public void storeProject(Set<DatabaseFile> files, String username, byte[] display) throws IOException {
        Project project = new Project(files, username, display);

        for(DatabaseFile file:files){
            file.setProject(project);
        }

        projectRepository.save(project);
    }
}
