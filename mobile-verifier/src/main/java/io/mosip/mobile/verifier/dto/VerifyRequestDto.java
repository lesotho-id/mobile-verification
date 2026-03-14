package io.mosip.mobile.verifier.dto;

public class VerifyRequestDto {

    private String idNumber;
    private String phone;
    private Boolean isCitizen;

    public VerifyRequestDto() {
    }

    public VerifyRequestDto(String idNumber, String phone,Boolean isCitizen) {
        this.idNumber = idNumber;
        this.phone = phone;
        this.isCitizen = isCitizen;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setidNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Boolean getIsCitizen() {
        return isCitizen;
    }

    public void setIsCitizen(Boolean isCitizen) {
        this.isCitizen = isCitizen;
    }
}