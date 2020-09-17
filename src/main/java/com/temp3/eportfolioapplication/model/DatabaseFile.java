package com.temp3.eportfolioapplication.model;

import lombok.Data;
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

    public DatabaseFile(String fileName, String fileType, byte[] data, String user) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.user = user;
    }
}
