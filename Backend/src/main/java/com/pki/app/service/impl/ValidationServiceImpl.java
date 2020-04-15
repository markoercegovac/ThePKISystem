package com.pki.app.service.impl;

import com.pki.app.service.KeyService;
import com.pki.app.service.KeystoreService;
import com.pki.app.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    private final KeystoreService keystoreService;
    private final KeyService keyService;

    @Override
    public boolean validate(String alias) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        KeyStore keyStore=keystoreService.getKeyStore(keyService.getKeyStorePath(),keyService.getKeyStorePass());
        X509Certificate certificate= (X509Certificate) keyStore.getCertificate(alias);
        Certificate[] chain=keyStore.getCertificateChain(alias);

        return false;
    }

    @Override
    public boolean verifySignature(String alias, PublicKey issuerPublicKey) {
        return false;
    }

    @Override
    public boolean validate(Certificate certificate) {
        return false;
    }
}
