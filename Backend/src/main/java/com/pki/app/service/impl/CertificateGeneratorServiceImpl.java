package com.pki.app.service.impl;

import com.pki.app.dto.SubjectDto;
import com.pki.app.enumeration.CertificateType;
import com.pki.app.model.Certificate;
import com.pki.app.model.IssuerData;
import com.pki.app.model.SubjectData;
import com.pki.app.repository.CertificateRepository;
import com.pki.app.service.CertificateGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CertificateGeneratorServiceImpl implements CertificateGeneratorService {

    @Autowired
    CertificateRepository repository;

    @Override
    public void saveCertificateDB(SubjectDto subjectData) {

        CertificateType cerType;
        if (subjectData.getType().equals("ROOT")) {
            cerType = CertificateType.ROOT;
        } else if (subjectData.getType().equals("INTERMEDIATE")) {
            cerType = CertificateType.INTERMEDIATE;
        } else {
            cerType = CertificateType.CLIENT;
        }

        if (subjectData.getStartDate().compareTo(subjectData.getEndDate()) < 0) {
            Certificate certificate = new Certificate();
            certificate.setSerialNumber(subjectData.getSerialNumber());
            certificate.setType(cerType);
            certificate.setValid(true);
            certificate.setSubjectCommonName(subjectData.getName()+subjectData.getSurname()+"organizacija:"+subjectData.getOrganization());

            repository.save(certificate);
        }
    }

    @Override
    public X509Certificate generateCertificate(SubjectData subjectData, IssuerData issuerData) {
        return null;
    }

    @Override
    public ArrayList<Certificate> getAllCertificates() {

        ArrayList<Certificate> sertifikati = new ArrayList();

        sertifikati.addAll(repository.findAllByTypeAndValid(CertificateType.ROOT,true));
        for(Certificate cert : repository.findAllByTypeAndValid(CertificateType.INTERMEDIATE,true)) {
            sertifikati.add(cert);
        }
        return sertifikati;
    }


}
