package com.pki.app.dto;

import lombok.Data;
import org.bouncycastle.asn1.x500.X500Name;

import java.security.PrivateKey;

@Data
public class IssuerDto {
    private X500Name x500Name;
    private PrivateKey privateKey;
    private String alias;
}
