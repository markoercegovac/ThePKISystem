package com.pki.app.dto;

import com.pki.app.enumeration.CertificateType;
import lombok.Data;
import org.bouncycastle.asn1.x500.X500Name;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

@Data
public class SubjectDto implements DtoEntity {
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private X500Name x500Name;
    private String serialNumber;
    private Date startDate;
    private Date endDate;

    //za X500Name
    private String name;
    private String surname;
    private String organization;
    private String organizationUnit;
    private String email;
    private String country;
    //ovo nam pomaze za odredjivanje tipa (samopotpisani,intermeijer,klijent)
    private String type;
    private String alias;
    private String issuerSerialNumber;
}
