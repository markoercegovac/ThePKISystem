package com.pki.app.service.impl;

import com.pki.app.dto.CertificateDto;
import com.pki.app.dto.IssuerDto;
import com.pki.app.dto.SubjectDto;
import com.pki.app.model.IssuerData;
import com.pki.app.model.SubjectData;
import com.pki.app.service.CertificateService;
import com.pki.app.service.KeyService;
import com.pki.app.service.KeystoreService;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final KeystoreService keystoreService;
    private final KeyService keyService;

    @Override
    public X509Certificate generateCertificate(SubjectDto subjectDto, IssuerDto issuerDto, CertificateDto certificateDto) throws OperatorCreationException, NoSuchAlgorithmException, CertificateException, NoSuchProviderException, InvalidAlgorithmParameterException {
        JcaContentSignerBuilder builder=new JcaContentSignerBuilder("SHA256withECDSA");
        Security.addProvider(new BouncyCastleProvider());

        //treba dodati jos templejt

        X500Name x500NameSubject=getX500NameSubject(subjectDto);
        X500Name x500NameIssuer=getX500NameIssuer();

        subjectDto.setPublicKey(keyService.generateKeyPair().getPublic());
        issuerDto.setPrivateKey(keyService.generateKeyPair().getPrivate());

        builder=builder.setProvider("BC");
        ContentSigner contentSigner=builder.build(issuerDto.getPrivateKey());
        BigInteger serialNumber=new BigInteger(1,keyService.getSerialNumber());
        X509v3CertificateBuilder certificateBuilder=new JcaX509v3CertificateBuilder(x500NameIssuer,serialNumber,
                subjectDto.getStartDate(),subjectDto.getEndDate(),x500NameSubject,subjectDto.getPublicKey());
        return new JcaX509CertificateConverter().setProvider(new BouncyCastleProvider()).getCertificate(certificateBuilder.build(contentSigner));
    }

    @Override
    public void createCertificate(SubjectDto subjectDto, IssuerDto issuerDto) throws CertificateException, NoSuchAlgorithmException, OperatorCreationException, IOException, KeyStoreException, NoSuchProviderException, InvalidAlgorithmParameterException {
        X509Certificate cert=generateCertificate(subjectDto,issuerDto,new CertificateDto());
        Certificate[] newChain=getCertificateChain(subjectDto.getAlias(),cert);
        keystoreService.store(keyService.getKeyStorePass(),"key",newChain,issuerDto.getPrivateKey(),issuerDto.getAlias(),keyService.getKeyStorePath());
    }

    @Override
    public X500Name getX500NameSubject(SubjectDto subjectDto) {
        X500NameBuilder builder=new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.CN,subjectDto.getName());
        builder.addRDN(BCStyle.O, subjectDto.getOrganization());
        builder.addRDN(BCStyle.C, subjectDto.getCountry());
        builder.addRDN(BCStyle.SURNAME, subjectDto.getSurname());
        builder.addRDN(BCStyle.OU, subjectDto.getOrganizationUnit());
        return builder.build();
    }

    @Override
    public X500Name getX500NameIssuer() {
        X500NameBuilder builder=new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.CN,"system.com");
        builder.addRDN(BCStyle.O, "test.com");
        builder.addRDN(BCStyle.C, "Srbija");
        return builder.build();
    }

    @Override
    public Certificate[] getCertificateChain(String alias,Certificate certificate) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        KeyStore keyStore = keystoreService.getKeyStore(keyService.getKeyStorePath(),keyService.getKeyStorePass());
        Certificate[] chain=keyStore.getCertificateChain(alias);
        List<Certificate> certificateList= new ArrayList<>(Arrays.asList(chain));
        certificateList.add(0,certificate);

        Certificate[] newChain=new Certificate[certificateList.size()];
        for(int i=0;i<certificateList.size();i++){
            newChain[i]=certificateList.get(i);
        }
        return newChain;
    }


}
