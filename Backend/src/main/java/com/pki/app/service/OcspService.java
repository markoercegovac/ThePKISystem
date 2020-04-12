package com.pki.app.service;

import com.pki.app.dto.CertificateDto;

public interface OcspService {
    void revoke(CertificateDto certificateDto);
    void check(String serialNumber);
}
