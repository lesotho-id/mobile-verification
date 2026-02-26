package io.mosip.mobile.verifier.controller;


import io.mosip.mobile.verifier.dto.MobileVerifierResponseDto;
import io.mosip.mobile.verifier.service.MobileVerifierService;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MobileVerifierController {

    private final MobileVerifierService identityService;

    public MobileVerifierController(MobileVerifierService identityService) {
        this.identityService = identityService;
    }

    @GetMapping("/verify")
    public MobileVerifierResponseDto process(
            @RequestParam("idNumber") String idNumber,
            @RequestParam("phone") String phone) {

        return identityService.process(idNumber, phone);
    }
}
