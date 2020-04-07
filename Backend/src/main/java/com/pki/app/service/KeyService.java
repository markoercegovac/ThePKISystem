package com.pki.app.service;

import java.security.NoSuchAlgorithmException;

public interface KeyService {
    byte[] getSerialNumber() throws NoSuchAlgorithmException;
    String getKeyStorePass();
    String getKeyStorePath();
}

