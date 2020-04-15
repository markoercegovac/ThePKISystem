package com.pki.app.service.impl;

import com.pki.app.dto.CertificateDto;
import com.pki.app.service.KeyService;
import com.pki.app.service.KeystoreService;

import java.security.*;
import java.security.cert.Certificate;

import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;


@Service
@RequiredArgsConstructor
public class KeystoreServiceImpl implements KeystoreService {

    private final KeyService keyService;

    @Override
    public void store(String keyStorePassword, String keyPassword, Certificate[] chain, PrivateKey privateKey, String alias,String keyStorePath) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        char[] keyStorePasswordChars=keyStorePassword.toCharArray();
        char[] keyPasswordChars=keyPassword.toCharArray();

        // znaci get Key store pokusava da pronadje keyStore na zadatoj putanji,ako ga ne pronadje kreira novi
        KeyStore keyStore=getKeyStore(keyStorePath,keyStorePassword);
        KeyStore.PrivateKeyEntry privKeyEntry = new KeyStore.PrivateKeyEntry(privateKey,
                chain);
        keyStore.setEntry(alias, privKeyEntry, new KeyStore.PasswordProtection(keyPasswordChars));
        keyStore.store(new FileOutputStream(keyStorePath),keyStorePasswordChars);
    }

    @Override
    public KeyStore getKeyStore(String keyStorePath, String keyStorePassword) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        char[] keyStorePasswordChars=keyStorePassword.toCharArray();
        KeyStore keyStore=KeyStore.getInstance("PKCS12");
        try{
            keyStore.load(new FileInputStream(keyStorePath),keyStorePasswordChars);
        }catch (FileNotFoundException e){
            keyStore.load(null,keyStorePasswordChars);
        }
        return keyStore;
    }

    public List<X509Certificate> getCertificates(String keyStorePass) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        String keyStorePath=keyService.getKeyStorePath();
        KeyStore keyStore=getKeyStore(keyStorePath,keyStorePass);
        List<X509Certificate>certificateList=new ArrayList<>();
        Enumeration<String> aliass= keyStore.aliases();

        while(aliass.hasMoreElements()){
            String alias=aliass.nextElement();
            X509Certificate certificate=(X509Certificate)keyStore.getCertificate(alias);
            certificateList.add(certificate);
        }
        return certificateList;
    }

}
