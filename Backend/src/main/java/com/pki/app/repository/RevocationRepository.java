package com.pki.app.repository;

import com.pki.app.model.RevocationCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevocationRepository extends JpaRepository<RevocationCertificate,Long> {
    RevocationCertificate findBySerial(String serial);
}
