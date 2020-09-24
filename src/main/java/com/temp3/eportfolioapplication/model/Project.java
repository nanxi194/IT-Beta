package com.temp3.eportfolioapplication.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @EqualsAndHashCode.Exclude
    @OneToMany
    private Set<DatabaseFile> files;


    public Project(Set<DatabaseFile> files, String username){
        this.files = files;
        this.username = username;
    }

    public Project(Set<DatabaseFile> files, String username, byte[] display){
        this.files = files;
        this.username = username;
        this.display = display;
    }
}
