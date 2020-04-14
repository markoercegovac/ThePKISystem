package com.pki.app.repository;

import com.pki.app.model.Certificate;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepositoryImplementation <Certificate,String> {

}
