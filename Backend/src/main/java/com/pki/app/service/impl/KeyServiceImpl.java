package com.pki.app.service.impl;

import com.pki.app.service.KeyService;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class KeyServiceImpl implements KeyService {
    @Override
    public byte[] getSerialNumber() throws NoSuchAlgorithmException {
        SecureRandom secureRandom=null;
        secureRandom=SecureRandom.getInstance("NativePRNG");
        byte[] values = new byte[124];
        secureRandom.nextBytes(values);
        return values;
    }

    @Override
    public String getKeyStorePass() {
        return "password";
    }

    @Override
    public String getKeyStorePath() {
        return "test.jks";
    }
}
