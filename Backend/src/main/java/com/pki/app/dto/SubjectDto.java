package com.pki.app.dto;

import lombok.Data;
import org.bouncycastle.asn1.x500.X500Name;

import java.security.PublicKey;
import java.util.Date;

@Data
public class SubjectDto {
    private PublicKey publicKey;
    private X500Name x500Name;
    private String serialNumber;
    private Date startDate;
    private Date endDate;
}
