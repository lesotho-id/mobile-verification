package io.mosip.mobileVerifier.exception;

public class ValidationException extends RuntimeException {

    private final String errorCode;

    public ValidationException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
