package io.mosip.mobile.verifier.service;

import io.mosip.mobile.verifier.dto.MobileVerifierResponseDto;
import io.mosip.mobile.verifier.dto.VerifyRequestDto;
import io.mosip.mobile.verifier.exception.MobileVerifierErrorCodes;
import io.mosip.mobile.verifier.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static io.mosip.mobile.verifier.exception.MobileVerifierErrorCodes.*;


@Service
public class MobileVerifierServiceImpl implements MobileVerifierService {

    @Value("${verify.api.url}")
    private String verifyApiUrl;
    @Value("${verify.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final Logger LOGGER =
            LoggerFactory.getLogger(MobileVerifierServiceImpl.class);

    @Override
    public MobileVerifierResponseDto process(String idNumber, String phone,Boolean isCitizen) {

        LOGGER.info("Mobile verification request received");

        try {
            validateInputs(idNumber, phone, isCitizen);

            VerifyRequestDto request = new VerifyRequestDto();
            request.setidNumber(idNumber);
            request.setPhone(phone);
            request.setIsCitizen(isCitizen);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-api-key", apiKey);

            HttpEntity<VerifyRequestDto> entity = new HttpEntity<>(request, headers);

            LOGGER.info("Calling external verification API: {}", verifyApiUrl);

            ResponseEntity<String> response =
                    restTemplate.postForEntity(verifyApiUrl, entity, String.class);

            LOGGER.info("External API Status: {}", response.getStatusCode());
            LOGGER.info("External API Response: {}", response.getBody());

            if (response.getStatusCode().is2xxSuccessful()) {

                return new MobileVerifierResponseDto(
                        "SUCCESS",
                        "Mobile number verified successfully"
                );

            } else {

                LOGGER.warn("Verification failed with status: {}", response.getStatusCode());

                return new MobileVerifierResponseDto(
                        "FAILURE",
                        "Verification failed. Please verify details."
                );
            }

        } catch (ValidationException ex) {

            LOGGER.warn("Validation failure: {}", ex.getErrorCode());

            return new MobileVerifierResponseDto(
                    "FAILURE",
                    resolveErrorMessage(ex.getErrorCode()),
                    ex.getErrorCode()
            );

        } catch (Exception ex) {

            LOGGER.error("Error calling verification API", ex);

            return new MobileVerifierResponseDto(
                    "FAILURE",
                    "Verification service unavailable. Try again later.",
                    MobileVerifierErrorCodes.SYSTEM_ERROR
            );
        }
    }

    private void validateInputs(String idNumber, String phone,Boolean isCitizen) {

        if (idNumber == null || idNumber.isBlank()) {
            throw new ValidationException(EMPTY_INPUT);
        }

//        if (!idNumber.matches("[0-9]{12}")) {
//            throw new ValidationException(INVALID_NATIONAL_ID);
//        }

        if (phone == null || phone.isBlank()) {
            throw new ValidationException(EMPTY_INPUT);
        }

        if (!phone.matches("^[56][0-9]{7}$")) {
            throw new ValidationException(INVALID_PHONE);
        }

        if (isCitizen == null) {
            throw new ValidationException(EMPTY_INPUT);
        }


    }


    private String resolveErrorMessage(String errorCode) {

        if (EMPTY_INPUT.equals(errorCode)) {
            return "Input fields must not be empty";
        } else if (INVALID_NATIONAL_ID.equals(errorCode)) {
            return "National idNumber must be 12 digits";
        } else if (INVALID_PHONE.equals(errorCode)) {
            return "Invalid phone number format";
        }

        return "Invalid request";
    }
}
