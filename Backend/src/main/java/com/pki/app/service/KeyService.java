package com.pki.app.service;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public interface KeyService {
    BigInteger getSerialNumber() throws NoSuchAlgorithmException;
    String getKeyStorePass();
    String getKeyStorePath();
    KeyPair generateKeyPair() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException;
}

