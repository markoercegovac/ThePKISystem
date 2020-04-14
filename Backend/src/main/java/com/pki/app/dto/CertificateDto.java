package com.pki.app.dto;

import com.pki.app.enumeration.CertificateType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class CertificateDto{


    private String serialNumber;

    private CertificateType type;

    private boolean valid;

}
