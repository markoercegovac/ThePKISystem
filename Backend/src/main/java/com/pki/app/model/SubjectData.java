package com.pki.app.model;

import lombok.Data;
import org.bouncycastle.asn1.x500.X500Name;

import java.security.PublicKey;
import java.util.Date;

@Data
public class SubjectData {
    private PublicKey publicKey;
    private X500Name x500Name;
    private String serialNumber;
    private Date startDate;
    private Date endDate;
    private String name;
    private String surname;
    private String organization;
    private String organizationUnit;
    private String email;
    private String country;
    private String   type;

}
