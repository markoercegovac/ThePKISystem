package com.pki.app.service;

import com.pki.app.dto.CertificateDto;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public interface OcspService {
    void revoke(CertificateDto certificateDto);
    void check(String serialNumber);
    void revokeChildren(CertificateDto certificate) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException;
}
