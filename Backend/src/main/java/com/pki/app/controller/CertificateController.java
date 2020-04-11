package com.pki.app.controller;

import com.pki.app.model.Proba;
import com.pki.app.model.SubjectData;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/generate")
public class CertificateController {

    @PostMapping
    public void generateCertificate(@RequestBody SubjectData subjectData){
        Date d=subjectData.getStartDate();
    }

    //za ispis tabele
    @GetMapping("/allCertificates")
    public ResponseEntity<List<Proba>> getCertificates(){
        //  public List<Proba> getCertificates() {
        //lista gde cu smestiti nekoliko 'Proba'
        List<Proba> probaList = new ArrayList<>();

        Proba proba1= new Proba(1, "SB1","12/5/2010","12/5/2015", "A" );
        Proba proba2= new Proba(2, "SB2","12/5/2011","12/5/2015", "B" );
        Proba proba3= new Proba(3, "SB3","12/5/2012","12/5/2015", "C" );

            probaList.add(proba1);
            probaList.add(proba2);
            probaList.add(proba3);

            return new ResponseEntity<>(probaList, HttpStatus.OK);
    }

}
