package com.pki.app.service;

import com.pki.app.dto.DtoEntity;
import com.pki.app.dto.IssuerDto;
import com.pki.app.dto.SubjectDto;
import com.pki.app.model.IssuerData;
import com.pki.app.model.SubjectData;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.operator.OperatorCreationException;

import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public interface CertificateService {
    X509Certificate generateCertificate(SubjectDto subjectDto, IssuerDto issuerDto) throws OperatorCreationException, NoSuchAlgorithmException, CertificateException, IOException, KeyStoreException, UnrecoverableKeyException;
    void createCertificate(SubjectDto subjectDto, IssuerDto issuerDto) throws CertificateException, NoSuchAlgorithmException, OperatorCreationException, IOException, KeyStoreException, UnrecoverableKeyException;
    X500Name getX500NameSubject(DtoEntity subjectDto);
    X500Name getX500NameIssuer();
    Certificate[] getCertificateChain(String alias,Certificate certificate) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException;
}
