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

        KeyStore keyStore=getKeyStore(keyStorePath,keyStorePassword);
        keyStore.setKeyEntry(alias,privateKey,keyPasswordChars,chain);
        keyStore.store(new FileOutputStream(keyStorePath),keyStorePasswordChars);
    }

    @Override
    public KeyStore getKeyStore(String keyStorePath, String keyStorePassword) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        char[] keyStorePasswordChars=keyStorePassword.toCharArray();
        KeyStore keyStore=KeyStore.getInstance(KeyStore.getDefaultType());
        try{
            keyStore.load(new FileInputStream(keyStorePath),keyStorePasswordChars);
        }catch (FileNotFoundException e){
            keyStore.load(null,keyStorePasswordChars);
        }
        return keyStore;
    }

    @Override
    public List<CertificateDto> getCertificates(String keyStorePass) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        String keyStorePath=keyService.getKeyStorePath();
        KeyStore keyStore=getKeyStore(keyStorePath,keyStorePass);
        List<CertificateDto>certificateList=new ArrayList<>();
        Enumeration<String> aliases = keyStore.aliases();
        while(aliases.hasMoreElements()){
            String alias=aliases.nextElement();
            Certificate[] certificates=keyStore.getCertificateChain(alias);
            CertificateDto certificateDto=new CertificateDto();
            certificateDto.setSerialNumber(((X509Certificate)certificates[0]).getSerialNumber().toString());
            certificateDto.setValid(true);
            certificateList.add(certificateDto);

            //TODO NM: Uradi konverter iz lanca sertifikata u listu nasih dto sertifikata i ovu metodu koristi na kontrolleru
        }
        return certificateList;
    }


}
