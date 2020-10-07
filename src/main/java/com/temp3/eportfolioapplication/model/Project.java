package com.temp3.eportfolioapplication.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String username;


    @Lob
    private byte[] display;

    private String projectName;


    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "project")
    private Set<DatabaseFile> files;


    public Project(Set<DatabaseFile> files, String username, String projectName){
        this.files = files;
        this.username = username;
        this.projectName = projectName;
    }

    public Project(Set<DatabaseFile> files, String username, byte[] display, String ProjectName){
        this.files = files;
        this.username = username;
        this.display = display;
        this.projectName = ProjectName;
    }
}
