package com.pki.app.service;

import com.pki.app.dto.SubjectDto;
import com.pki.app.model.Certificate;
import com.pki.app.model.IssuerData;
import com.pki.app.model.SubjectData;
import org.springframework.stereotype.Service;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public interface CertificateGeneratorService {

    void saveCertificateDB(SubjectDto subjectData);
    X509Certificate generateCertificate(SubjectData subjectData, IssuerData issuerData);
    ArrayList<Certificate> getAllCertificates();

}
