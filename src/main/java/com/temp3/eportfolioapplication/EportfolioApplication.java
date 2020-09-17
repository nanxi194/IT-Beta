package com.temp3.eportfolioapplication;

import com.temp3.eportfolioapplication.model.User;
import com.temp3.eportfolioapplication.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EportfolioApplication {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(EportfolioApplication.class, args);
    }

//    @PostConstruct
//    public void setup(){
//        User user1 = new User("alice", "bob", "email@email", "user", "password");
//        userRepository.save(user1);
//    }



}
