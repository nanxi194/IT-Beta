package com.temp3.eportfolioapplication.controller;

import com.temp3.eportfolioapplication.service.DocumentService;
import com.temp3.eportfolioapplication.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @GetMapping("/document/{username}/{id}")
    public void getUserDocument(@PathVariable String username, @PathVariable String id, HttpServletResponse response) throws IOException {
        documentService.writeDocumentToResponse(Long.parseLong(id), response);
    }
}
