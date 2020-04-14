package com.pki.app.service;

import com.pki.app.dto.CertificateDto;
import com.pki.app.model.Certificate;
import com.pki.app.model.IssuerData;
import com.pki.app.model.SubjectData;
import org.springframework.stereotype.Service;

import java.security.cert.X509Certificate;

@Service
public interface CertificateGeneratorService {

    void saveCertificateDB(Certificate certificate);
    X509Certificate generateCertificate(SubjectData subjectData, IssuerData issuerData);
}
