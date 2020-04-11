package com.pki.app.service;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public interface KeyService {
    byte[] getSerialNumber() throws NoSuchAlgorithmException;
    String getKeyStorePass();
    String getKeyStorePath();
    KeyPair generateKeyPair() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException;
}

