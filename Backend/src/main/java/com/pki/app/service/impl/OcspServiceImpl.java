package com.pki.app.service.impl;

import com.pki.app.dto.CertificateDto;
import com.pki.app.mapper.DtoUtils;
import com.pki.app.model.RevocationCertificate;
import com.pki.app.repository.RevocationRepository;
import com.pki.app.service.KeyService;
import com.pki.app.service.KeystoreService;
import com.pki.app.service.OcspService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OcspServiceImpl implements OcspService {

    private final RevocationRepository revocationRepository;
    private final KeystoreService keystoreService;
    private final KeyService keyService;
    private final DtoUtils mapper;

    @Override
    public void revoke(CertificateDto certificateDto) {

    }

    @Override
    public void check(String serialNumber) {
        if(revocationRepository.findBySerial(serialNumber).isValid());
    }

    @Override
    public void revokeChildren(CertificateDto certificate) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        KeyStore keyStore=keystoreService.getKeyStore(keyService.getKeyStorePath(),keyService.getKeyStorePass());
        Enumeration<String> aliases=keyStore.aliases();
        while (aliases.hasMoreElements()){
            String alias=aliases.nextElement();
            Certificate[] certificates=keyStore.getCertificateChain(alias);
            List<Certificate>certificateList=new ArrayList<>(Arrays.asList(certificates));
            if(certificateList.contains(certificate)){
                for(Certificate cert:certificateList){
                    X509Certificate x509Certificate=(X509Certificate)cert;
                    String serial=x509Certificate.getSerialNumber().toString();
                    if(cert.equals(certificate)){
                        break;
                    }
                    RevocationCertificate revocationCertificate=new RevocationCertificate();
                    revocationCertificate.setSerial(((X509Certificate) cert).getSerialNumber().toString());
                    revocationRepository.save(revocationCertificate);
                }
            }
        }
    }
}
