package com.temp3.eportfolioapplication.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "files")
public class DatabaseFile {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    private String user;

    private String description;


    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Project project;

    public DatabaseFile(String fileName, String fileType, byte[] data, String user, String description) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.user = user;
        this.description = description;
    }

}
