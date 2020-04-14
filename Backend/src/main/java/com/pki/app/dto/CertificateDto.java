package com.pki.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;

import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Date;

@AllArgsConstructor
@Data
public class CertificateDto {
    private String subjName;
    private String subjOrgName; //ime organizacije
    private String subjCountry;

    private String issName;
    private String issOrgName; //ime organizacije
    private String issCountry;

    private String serialNumber;
    private Date endDate;
    private Date startDate;


    public CertificateDto() { }

    public CertificateDto(Certificate cert) throws CertificateEncodingException {

        //ime subjekta kastovano
        X500Name subjName = new JcaX509CertificateHolder((X509Certificate) cert).getSubject();
        RDN subjN = subjName.getRDNs(BCStyle.CN)[0];
        String sname = IETFUtils.valueToString(subjN.getFirst().getValue());
        this.subjName = sname;

        //ime organizacije subjekta kastovano
        RDN on = subjName.getRDNs(BCStyle.O)[0];
        String oname = IETFUtils.valueToString(on.getFirst().getValue());
        this.subjOrgName = oname;

        //ime drzave subjekta kastovano
        RDN con = subjName.getRDNs(BCStyle.C)[0];
        String conname = IETFUtils.valueToString(con.getFirst().getValue());
        this.subjCountry = conname;

        this.serialNumber = String.valueOf(((X509Certificate) cert).getSerialNumber());
        this.startDate = ((X509Certificate) cert).getNotBefore();
        this.endDate = ((X509Certificate) cert).getNotAfter();

        //ime  issuer-a kastovano
        X500Name issName = new JcaX509CertificateHolder((X509Certificate) cert).getIssuer();
        RDN isn = issName.getRDNs(BCStyle.CN)[0];
        String isname = IETFUtils.valueToString(isn.getFirst().getValue());
        this.issName = isname;

        //ime organizacije issuer-a kastovano
        RDN ion = issName.getRDNs(BCStyle.O)[0];
        String iname = IETFUtils.valueToString(on.getFirst().getValue());
        this.subjOrgName = iname;

        //ime drzave issuer-a kastovano
        RDN icon = issName.getRDNs(BCStyle.C)[0];
        String icount = IETFUtils.valueToString(con.getFirst().getValue());
        this.subjCountry = icount;



    }



    }
