package com.pki.app.model;


import com.pki.app.enumeration.CertificateType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Certificate {

    @Id
    private String serialNumber;

    @Column
    private CertificateType type;

    @Column
    private boolean valid;
}
