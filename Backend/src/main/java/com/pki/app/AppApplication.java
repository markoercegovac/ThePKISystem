package com.pki.app;

import com.pki.app.service.KeystoreService;
import com.pki.app.service.impl.KeyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.KeyStore;


@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {

        SpringApplication.run(AppApplication.class, args);
    }

}
