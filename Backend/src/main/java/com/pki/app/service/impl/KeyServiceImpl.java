package com.pki.app.service.impl;

import com.pki.app.service.KeyService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

@Service
public class KeyServiceImpl implements KeyService {
    @Override
    public BigInteger getSerialNumber() throws NoSuchAlgorithmException {
        return new BigInteger(64, new SecureRandom());
    }

    @Override
    public String getKeyStorePass() {
        return "password";
    }

    @Override
    public String getKeyStorePath() {
        return "test.pks";
    }

    @Override
    public KeyPair generateKeyPair() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "SunEC");
        ECGenParameterSpec ecsp=new ECGenParameterSpec("secp256k1");

        kpg.initialize(ecsp);
        return kpg.generateKeyPair();
    }
}
