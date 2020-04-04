package com.pki.app.controller;

import com.pki.app.model.SubjectData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/generate")
public class CertificateController {

    @PostMapping
    public void generateCertificate(@RequestBody SubjectData subjectData){
        Date d=subjectData.getStartDate();
    }
}
