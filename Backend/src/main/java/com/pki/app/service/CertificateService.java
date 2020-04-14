package com.pki.app.service;

import com.pki.app.dto.CertificateDto;
import com.pki.app.dto.IssuerDto;
import com.pki.app.dto.SubjectDto;
import com.pki.app.model.IssuerData;
import com.pki.app.model.SubjectData;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.operator.OperatorCreationException;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public interface CertificateService {
    X509Certificate generateCertificate(SubjectDto subjectDto, IssuerDto issuerDto, CertificateDto certificateDto) throws OperatorCreationException, NoSuchAlgorithmException, CertificateException, NoSuchProviderException, InvalidAlgorithmParameterException;
    void createCertificate(SubjectDto subjectDto, IssuerDto issuerDto) throws CertificateException, NoSuchAlgorithmException, OperatorCreationException, IOException, KeyStoreException, NoSuchProviderException, InvalidAlgorithmParameterException;
    X500Name getX500NameSubject(SubjectDto subjectDto);
    X500Name getX500NameIssuer();
    Certificate[] getCertificateChain(String alias,Certificate certificate) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException;
}
