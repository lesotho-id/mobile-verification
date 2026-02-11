package src.main.java.io.mosip.mobile.verifier.service;

//import io.mosip.mobile.verifier.dto.MobileVerifierResponseDto;
//import io.mosip.mobile.verifier.exception.MobileVerifierErrorCodes;
//import io.mosip.mobile.verifier.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import src.main.java.io.mosip.mobile.verifier.dto.MobileVerifierResponseDto;
import src.main.java.io.mosip.mobile.verifier.exception.MobileVerifierErrorCodes;
import src.main.java.io.mosip.mobile.verifier.exception.ValidationException;

@Service
public class MobileVerifierServiceImpl implements MobileVerifierService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(MobileVerifierServiceImpl.class);

    @Override
    public MobileVerifierResponseDto process(String referenceIdentityNumber, String phoneNumber) {

        LOGGER.info("Mobile verification request received");

        try {
            validateInputs(referenceIdentityNumber, phoneNumber);
            LOGGER.info(
                    "Mobile verification successful | referenceIdentityNumber={} | phoneNumberEnding={}",
                    referenceIdentityNumber,
                    phoneNumber
            );

            return new MobileVerifierResponseDto(
                    "SUCCESS",
                    "Mobile number verified successfully"
            );

        } catch (ValidationException ex) {

            LOGGER.warn("Validation failure: {}", ex.getErrorCode());

            return new MobileVerifierResponseDto(
                    "FAILURE",
                    resolveErrorMessage(ex.getErrorCode()),
                    ex.getErrorCode()
            );

        } catch (Exception ex) {

            LOGGER.error("Unexpected error during mobile verification", ex);

            return new MobileVerifierResponseDto(
                    "FAILURE",
                    "Unable to process request. Please try again later.",
                    MobileVerifierErrorCodes.SYSTEM_ERROR
            );
        }
    }

    private void validateInputs(String referenceIdentityNumber, String phoneNumber) {

        if (referenceIdentityNumber == null || referenceIdentityNumber.isBlank()) {
            throw new ValidationException(MobileVerifierErrorCodes.EMPTY_INPUT);
        }

        if (!referenceIdentityNumber.matches("[0-9]{12}")) {
            throw new ValidationException(MobileVerifierErrorCodes.INVALID_NATIONAL_ID);
        }

        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new ValidationException(MobileVerifierErrorCodes.EMPTY_INPUT);
        }

        if (!phoneNumber.matches("[6-9][0-9]{9}")) {
            throw new ValidationException(MobileVerifierErrorCodes.INVALID_PHONE);
        }
    }


    private String resolveErrorMessage(String errorCode) {

        if (MobileVerifierErrorCodes.EMPTY_INPUT.equals(errorCode)) {
            return "Input fields must not be empty";
        } else if (MobileVerifierErrorCodes.INVALID_NATIONAL_ID.equals(errorCode)) {
            return "National referenceIdentityNumber must be 12 digits";
        } else if (MobileVerifierErrorCodes.INVALID_PHONE.equals(errorCode)) {
            return "Invalid phone number format";
        }

        return "Invalid request";
    }
}
