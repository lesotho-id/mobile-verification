package src.main.java.io.mosip.mobile.verifier.controller;

//import io.mosip.mobile.verifier.dto.MobileVerifierResponseDto;
//import io.mosip.mobile.verifier.service.MobileVerifierService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import src.main.java.io.mosip.mobile.verifier.dto.MobileVerifierResponseDto;
import src.main.java.io.mosip.mobile.verifier.service.MobileVerifierService;

@RestController
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
