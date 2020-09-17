package com.temp3.eportfolioapplication.service;

import com.temp3.eportfolioapplication.model.User;
import com.temp3.eportfolioapplication.model.UserDto;
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
    UserDetailsServiceImpl userDetailsService;

    public User registerNewUser(UserDto userDto){
        User newUser = new User(userDto);

        userRepository.save(newUser);

        userDetailsService.loadUserByUsername(newUser.getUsername());

        return newUser;
    }

}
