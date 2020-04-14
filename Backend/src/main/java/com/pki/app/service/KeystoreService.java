package com.pki.app.service;

import java.io.FileNotFoundException;
import java.security.cert.Certificate;

import com.pki.app.dto.CertificateDto;
import org.hibernate.mapping.PrimaryKey;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.util.List;

public interface KeystoreService {
    void store(String keyStorePassword,String keyPassword,Certificate[] chain,PrivateKey privateKey,String alias,String keyStorePath) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException;
    KeyStore getKeyStore(String keyStorePath,String keyStorePassword) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException;
    List<CertificateDto> getCertificates(String keyStorePass) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException;
}
