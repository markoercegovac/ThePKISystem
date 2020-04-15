package com.pki.app.service;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public interface ValidationService {
    boolean validate(String alias) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException;
    boolean verifySignature(String alias, PublicKey issuerPublicKey);
    boolean validate(Certificate certificate);
}
