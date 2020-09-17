package com.temp3.eportfolioapplication.model;


import com.sun.istack.NotNull;
import com.temp3.eportfolioapplication.validation.PasswordMatches;
import com.temp3.eportfolioapplication.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@PasswordMatches
public class UserDto {
    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String email;
}
