package io.mosip.mobileVerifier.controller;

import io.mosip.mobileVerifier.dto.MobileVerifierResponseDto;
import io.mosip.mobileVerifier.service.MobileVerifierService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/mobileverifier")
public class MobileVerifierController {

    private final MobileVerifierService identityService;

    public MobileVerifierController(MobileVerifierService identityService) {
        this.identityService = identityService;
    }

    @GetMapping("/verify")
    public MobileVerifierResponseDto process(
            @RequestParam("referenceIdentityNumber") String referenceIdentityNumber,
            @RequestParam("phone") String phone) {

        return identityService.process(referenceIdentityNumber, phone);
    }
}
