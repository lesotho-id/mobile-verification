package src.main.java.io.mosip.mobile.verifier.service;

//import io.mosip.mobile.verifier.dto.MobileVerifierResponseDto;

import src.main.java.io.mosip.mobile.verifier.dto.MobileVerifierResponseDto;

public interface MobileVerifierService {
    MobileVerifierResponseDto process(String referenceIdentityNumber, String phone);
}

