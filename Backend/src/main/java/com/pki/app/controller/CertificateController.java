package com.pki.app.controller;

import com.pki.app.dto.SubjectDto;
import com.pki.app.model.SubjectData;
import com.pki.app.service.CertificateService;
import com.pki.app.service.KeyService;
import com.pki.app.service.KeystoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Date;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/generate")
public class CertificateController {

    private CertificateService certificateGeneratorService;
    private final KeystoreService keystoreService;

    @PostMapping
    public void generateCertificate(@RequestBody SubjectData subjectData) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        Date d=subjectData.getStartDate();
        String pass="test";
        keystoreService.generateKeyStore(pass.toCharArray(),"test.jks");
    }
}
