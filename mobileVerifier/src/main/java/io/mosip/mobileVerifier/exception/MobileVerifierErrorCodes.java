package io.mosip.mobileVerifier.exception;

/**
 * Mobile Verifier Error Codes
 * Follows MOSIP standard error code format
 */
public final class MobileVerifierErrorCodes {

    private MobileVerifierErrorCodes() {

    }

    public static final String EMPTY_INPUT = "MV-VAL-001";
    public static final String INVALID_NATIONAL_ID = "MV-VAL-002";
    public static final String INVALID_PHONE = "MV-VAL-003";
    public static final String SYSTEM_ERROR = "MV-SYS-001";
}
