package com.temp3.eportfolioapplication;

import com.temp3.eportfolioapplication.controller.DocumentController;
import com.temp3.eportfolioapplication.controller.ProjectEditController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProjectEditControllerTests {

    @Autowired
    private ProjectEditController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
