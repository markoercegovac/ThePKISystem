package com.pki.app.service.impl;

import com.pki.app.service.KeyService;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

@Service
public class KeyServiceImpl implements KeyService {
    @Override
    public byte[] getSerialNumber() throws NoSuchAlgorithmException {
        SecureRandom secureRandom=null;
        secureRandom=SecureRandom.getInstance("Windows-PRNG");
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

    @Override
    public KeyPair generateKeyPair() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "SunEC");
        ECGenParameterSpec ecsp=new ECGenParameterSpec("secp256k1");

        kpg.initialize(ecsp);
        return kpg.generateKeyPair();
    }
}
