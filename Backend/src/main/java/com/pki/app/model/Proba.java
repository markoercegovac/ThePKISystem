package com.pki.app.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Proba {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String serijskiBroj;
    private String vaziOd;
    private String vaziDo;
    private String alijas;
}
