package com.temp3.eportfolioapplication.service;

import com.temp3.eportfolioapplication.model.User;
import com.temp3.eportfolioapplication.model.UserDto;
import com.temp3.eportfolioapplication.model.UserInfo;
import com.temp3.eportfolioapplication.repository.UserInfoRepository;
import com.temp3.eportfolioapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    public User registerNewUser(UserDto userDto){
        User newUser = new User(userDto);

        UserInfo userInfo = new UserInfo(newUser);

        userInfoRepository.save(userInfo);

        newUser.setUserInfo(userInfo);

        userRepository.save(newUser);

        userDetailsService.loadUserByUsername(newUser.getUsername());

        return newUser;
    }

}
