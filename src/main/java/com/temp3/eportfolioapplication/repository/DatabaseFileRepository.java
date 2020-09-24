package com.temp3.eportfolioapplication.repository;

import com.temp3.eportfolioapplication.model.DatabaseFile;
import com.temp3.eportfolioapplication.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;

@Repository
public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, Long> {

    DatabaseFile findById(long id);

    Iterable<DatabaseFile> findAllByUser(String username);

    Iterable<DatabaseFile> findAllByProject(Project project);
}
