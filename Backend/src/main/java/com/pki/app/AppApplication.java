package com.pki.app;

import com.pki.app.dto.IssuerDto;

import com.pki.app.service.CertificateService;
import com.pki.app.service.KeyService;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.KeyStore;


@SpringBootApplication
public class AppApplication {


    public IssuerDto issuerDto = new IssuerDto();

    public AppApplication() {
    }


    public static void main(String[] args) {

        SpringApplication.run(AppApplication.class, args);

    }


}
