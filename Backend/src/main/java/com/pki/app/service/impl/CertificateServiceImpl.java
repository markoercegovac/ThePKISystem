package com.pki.app.service.impl;

import com.pki.app.dto.IssuerDto;
import com.pki.app.dto.SubjectDto;
import com.pki.app.model.IssuerData;
import com.pki.app.model.SubjectData;
import com.pki.app.service.CertificateService;
import com.pki.app.service.KeyService;
import com.pki.app.service.KeystoreService;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final KeystoreService keystoreService;
    private final KeyService keyService;

    @Override
    public X509Certificate generateCertificate(SubjectDto subjectDto, IssuerDto issuerDto) throws OperatorCreationException, NoSuchAlgorithmException, CertificateException {
        JcaContentSignerBuilder builder=new JcaContentSignerBuilder("SHA256withECDSA");
        builder=builder.setProvider("BC");
        ContentSigner contentSigner=builder.build(issuerDto.getPrivateKey());
        BigInteger serialNumber=new BigInteger(1,keyService.getSerialNumber());
        X509v3CertificateBuilder certificateBuilder=new JcaX509v3CertificateBuilder(issuerDto.getX500Name(),serialNumber,
                subjectDto.getStartDate(),subjectDto.getEndDate(),subjectDto.getX500Name(),subjectDto.getPublicKey());
        return new JcaX509CertificateConverter().setProvider(new BouncyCastleProvider()).getCertificate(certificateBuilder.build(contentSigner));
    }

    @Override
    public void createCertificate(SubjectDto subjectDto, IssuerDto issuerDto) throws CertificateException, NoSuchAlgorithmException, OperatorCreationException, IOException, KeyStoreException {
        X509Certificate certificate=generateCertificate(subjectDto,issuerDto);
        String keyPass="key";
        keystoreService.store(keyService.getKeyStorePass(),keyPass,new Certificate[]{certificate},issuerDto.getPrivateKey(),
                "@test.com",keyService.getKeyStorePath());
    }
}
