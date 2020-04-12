package com.pki.app.dto;

import lombok.Data;

@Data
public class CertificateDto implements DtoEntity{
    private Long id;
    private String serialNumber;
    private String validFrom;
    private String validTo;
    private boolean isValid;
}
