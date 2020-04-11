package com.pki.app.controller;

import com.pki.app.enumeration.CertificateType;
import com.pki.app.model.Certificate;
import com.pki.app.model.SubjectData;

import com.pki.app.service.CertificateGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/generate")
public class CertificateController {

    @Autowired
    private  CertificateGeneratorService CGservice;

    @PostMapping
    public void generateCertificate(@RequestBody SubjectData subjectData){

        //dio za tip
        CertificateType cerType;
        if(subjectData.getType().equals("ROOT")) {
            cerType=CertificateType.ROOT;
        }
        else if(subjectData.getType().equals("INTERMEDIATE")){
            cerType=CertificateType.INTERMEDIATE;
        }
        else {
            cerType=CertificateType.CLIENT;
        }

        if(subjectData.getStartDate().compareTo(subjectData.getEndDate())<0) {
            Certificate certificate = new Certificate();
            certificate.setSerialNumber(subjectData.getSerialNumber());
            certificate.setType(cerType);
            certificate.setValid(true);
            CGservice.saveCertificateDB(certificate);
        }



    }
}
