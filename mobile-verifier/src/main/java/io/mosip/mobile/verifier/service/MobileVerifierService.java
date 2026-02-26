package io.mosip.mobile.verifier.service;

import io.mosip.mobile.verifier.dto.MobileVerifierResponseDto;

public interface MobileVerifierService {
    MobileVerifierResponseDto process(String idNumber, String phone);
}

