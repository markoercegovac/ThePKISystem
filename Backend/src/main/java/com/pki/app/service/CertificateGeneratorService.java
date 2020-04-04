package com.pki.app.service;

import com.pki.app.model.IssuerData;
import com.pki.app.model.SubjectData;

import java.security.cert.X509Certificate;

public interface CertificateGeneratorService {
    X509Certificate generateCertificate(SubjectData subjectData, IssuerData issuerData);
}
