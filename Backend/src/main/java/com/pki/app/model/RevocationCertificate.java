package com.pki.app.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="RevocationCertificate")
public class RevocationCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String serial;
    @Column
    private String validFrom;
    @Column
    private String validTo;
    @Column
    private boolean isValid;
}
