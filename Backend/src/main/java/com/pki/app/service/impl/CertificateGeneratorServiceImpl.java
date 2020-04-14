package com.pki.app.service.impl;

import com.pki.app.model.Certificate;
import com.pki.app.model.IssuerData;
import com.pki.app.model.SubjectData;
import com.pki.app.repository.CertificateRepository;
import com.pki.app.service.CertificateGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.cert.X509Certificate;

@Service
public class CertificateGeneratorServiceImpl implements CertificateGeneratorService {

    @Autowired
    CertificateRepository repository;

    @Override
    public void saveCertificateDB(Certificate certificate) {
        repository.save(certificate);
    }

    @Override
    public X509Certificate generateCertificate(SubjectData subjectData, IssuerData issuerData) {
        return null;
    }
}
