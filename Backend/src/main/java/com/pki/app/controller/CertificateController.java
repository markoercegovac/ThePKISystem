package com.pki.app.controller;

import com.pki.app.dto.IssuerDto;
import com.pki.app.dto.SubjectDto;
import com.pki.app.model.SubjectData;
import com.pki.app.service.CertificateService;
import com.pki.app.service.KeyService;
import com.pki.app.service.KeystoreService;

import lombok.RequiredArgsConstructor;
import org.bouncycastle.operator.OperatorCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.Date;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/generate")
public class CertificateController {

    private final CertificateService certificateService;
    private final KeystoreService keystoreService;
    private final KeyService keyService;

    @PostMapping
    public void generateCertificate(@RequestBody SubjectDto subjectDto) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, OperatorCreationException, NoSuchProviderException, InvalidAlgorithmParameterException {
        Date d=subjectDto.getStartDate();
        String pass="test";

        IssuerDto issuerDto=new IssuerDto();
        subjectDto.setX500Name(certificateService.getX500NameSubject());
        subjectDto.setPublicKey(keyService.generateKeyPair().getPublic());
        issuerDto.setX500Name(certificateService.getX500NameIssuer());
        issuerDto.setPrivateKey(keyService.generateKeyPair().getPrivate());
        keystoreService.getCertificates(keyService.getKeyStorePass());
//          certificateService.createCertificate(subjectDto,issuerDto);
    }
}
