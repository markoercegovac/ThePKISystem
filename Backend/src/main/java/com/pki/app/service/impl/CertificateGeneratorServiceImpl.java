package com.pki.app.service.impl;

import com.pki.app.model.IssuerData;
import com.pki.app.model.SubjectData;
import com.pki.app.service.CertificateGeneratorService;

import java.security.cert.X509Certificate;

public class CertificateGeneratorServiceImpl implements CertificateGeneratorService {
    @Override
    public X509Certificate generateCertificate(SubjectData subjectData, IssuerData issuerData) {
        return null;
    }
}
