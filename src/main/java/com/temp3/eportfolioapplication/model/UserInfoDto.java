package com.temp3.eportfolioapplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfoDto {

    private String firstName;

    private String lastName;

    private String contactInfo;

    private String location;

    private String education;

    private String workExp;

    private String summary;

    private byte[] picture;

    public UserInfoDto(String[] info, byte[] picture){
        this.firstName = info[0];
        this.lastName = info[1];
        this.contactInfo = info[2];
        this.location = info[3];
        this.education = info[4];
        this.workExp = info[5];
        this.summary = info[6];
        this.picture = picture;
    }
}
