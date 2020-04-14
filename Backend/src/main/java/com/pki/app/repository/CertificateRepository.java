package com.pki.app.repository;

import com.pki.app.enumeration.CertificateType;
import com.pki.app.model.Certificate;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CertificateRepository extends JpaRepositoryImplementation <Certificate,String> {

    Collection<Certificate> findAllByTypeAndValid(CertificateType ct,boolean b);
}
