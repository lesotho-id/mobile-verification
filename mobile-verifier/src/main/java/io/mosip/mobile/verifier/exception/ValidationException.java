package src.main.java.io.mosip.mobile.verifier.exception;

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
