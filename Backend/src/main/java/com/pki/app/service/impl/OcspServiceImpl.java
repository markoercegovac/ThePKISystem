package com.pki.app.service.impl;

import com.pki.app.dto.CertificateDto;
import com.pki.app.service.OcspService;
import org.springframework.stereotype.Service;

@Service
public class OcspServiceImpl implements OcspService {

    @Override
    public void revoke(CertificateDto certificateDto) {

    }

    @Override
    public void check(String serialNumber) {

    }
}
