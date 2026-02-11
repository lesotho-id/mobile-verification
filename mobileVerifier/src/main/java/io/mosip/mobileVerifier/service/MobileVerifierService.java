package io.mosip.mobileVerifier.service;

import io.mosip.mobileVerifier.dto.MobileVerifierResponseDto;

public interface MobileVerifierService {
    MobileVerifierResponseDto process(String referenceIdentityNumber, String phone);
}

