package com.temp3.eportfolioapplication.service;


import com.temp3.eportfolioapplication.model.UserInfo;
import com.temp3.eportfolioapplication.model.UserInfoDto;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    public UserInfo saveInfoChange(UserInfo userInfo, UserInfoDto userInfoDto){


        userInfo.setFirstName(userInfoDto.getFirstName());
        userInfo.setLastName(userInfoDto.getLastName());
        userInfo.setContactInfo(userInfoDto.getContactInfo());
        userInfo.setLocation(userInfoDto.getLocation());
        userInfo.setEducation(userInfoDto.getEducation());
        userInfo.setWorkExp(userInfoDto.getWorkExp());
        userInfo.setSummary(userInfoDto.getSummary());
        userInfo.setPicture(userInfoDto.getPicture());
        return userInfo;

    }
}
