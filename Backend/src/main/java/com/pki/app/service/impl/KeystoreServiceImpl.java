package com.pki.app.service.impl;

import com.pki.app.service.KeystoreService;

import java.security.*;
import java.security.cert.Certificate;
import org.hibernate.mapping.PrimaryKey;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


@Service
public class KeystoreServiceImpl implements KeystoreService {

    @Override
    public void generateKeyStore() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        String storeKey="password";
        char[] storeKeyChar=storeKey.toCharArray();

        KeyStore keyStore=KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null,storeKeyChar);

        try (FileOutputStream fos = new FileOutputStream("test.jks")) {
            keyStore.store(fos, storeKeyChar);
        }

    }

    @Override
    public void store(String keyStorePassword, String keyPassword, Certificate[] chain, PrivateKey privateKey, String alias,String keyStorePath) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        char[] keyStorePasswordChars=keyStorePassword.toCharArray();
        char[] keyPasswordChars=keyPassword.toCharArray();

        KeyStore keyStore=getKeyStore(keyStorePath,keyStorePassword);
        keyStore.setKeyEntry(alias,privateKey,keyPasswordChars,chain);
        keyStore.store(new FileOutputStream(keyStorePath),keyStorePasswordChars);
    }

    @Override
    public KeyStore getKeyStore(String keyStorePath, String keyStorePassword) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        char[] keyStorePasswordChars=keyStorePassword.toCharArray();
        KeyStore keyStore=KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(new FileInputStream(keyStorePath),keyStorePasswordChars);
        return keyStore;
    }

}
