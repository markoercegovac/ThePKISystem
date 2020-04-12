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

    @Column(name="Serial",nullable = true)
    private String serial;

    @Column(name="validFrom",nullable = true)
    private String validFrom;

    @Column(name="validTo",nullable = true)
    private String validTo;
}
