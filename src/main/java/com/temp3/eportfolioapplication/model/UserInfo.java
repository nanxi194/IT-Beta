package com.temp3.eportfolioapplication.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    private String contactInfo;

    private String location;

    private String education;

    private String workExp;

    @Lob
    private String summary;

    @Lob
    private byte[] picture;

    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = {CascadeType.ALL})
    private User user;

    public UserInfo(User user){
        this.firstName = "";
        this.lastName = "";
        this.contactInfo = "";
        this.location = "";
        this.education = "";
        this.workExp = "";
        this.summary = "";
        this.user = user;
        this.picture = null;
    }
}
