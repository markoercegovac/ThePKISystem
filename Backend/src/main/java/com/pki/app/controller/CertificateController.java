package com.pki.app.controller;


import com.pki.app.dto.CertificateDto;
import com.pki.app.model.Proba;
import com.pki.app.model.SubjectData;

import com.pki.app.service.OcspService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import com.pki.app.dto.IssuerDto;
import com.pki.app.dto.SubjectDto;

import com.pki.app.service.CertificateService;
import com.pki.app.service.KeyService;
import com.pki.app.service.KeystoreService;

import org.bouncycastle.operator.OperatorCreationException;




import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/generate")
public class CertificateController {

    private final CertificateService certificateService;
    private final KeystoreService keystoreService;
    private final KeyService keyService;
    private final OcspService ocspService;

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

    //za ispis tabele
    @GetMapping("/allCertificates")
    public ResponseEntity<List<CertificateDto>> getCertificates() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        List<CertificateDto> certificateDtoList = keystoreService.getCertificates(keyService.getKeyStorePass());
        return new ResponseEntity<>(certificateDtoList, HttpStatus.OK);
    }
    @PostMapping("/revoke")
    public void revokeCertificate(@RequestBody CertificateDto certificateDto){
        ocspService.revoke(certificateDto);
    }

}
