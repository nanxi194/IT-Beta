package com.temp3.eportfolioapplication;


import static org.assertj.core.api.Assertions.assertThat;
import com.temp3.eportfolioapplication.controller.SecurityController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;



@SpringBootTest
public class SecurityControllerTests {

    @Autowired
    private SecurityController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }


}
