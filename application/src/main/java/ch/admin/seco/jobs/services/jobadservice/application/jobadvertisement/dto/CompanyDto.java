package ch.admin.seco.jobs.services.jobadservice.application.jobadvertisement.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.Company;

public class CompanyDto {

    @NotBlank
    private String name;

    private String street;

    private String houseNumber;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String city;

    @Pattern(regexp = "[A-Z]{2}")
    private String countryIsoCode;

    private String postOfficeBoxNumber;

    private String postOfficeBoxPostalCode;

    private String postOfficeBoxCity;

    private String phone;

    private String email;

    private String website;

    private boolean surrogate;

    protected CompanyDto() {
        // For reflection libs
    }

    public CompanyDto(String name, String street, String houseNumber, String postalCode, String city, String countryIsoCode, String postOfficeBoxNumber, String postOfficeBoxPostalCode, String postOfficeBoxCity, String phone, String email, String website, boolean surrogate) {
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.countryIsoCode = countryIsoCode;
        this.postOfficeBoxNumber = postOfficeBoxNumber;
        this.postOfficeBoxPostalCode = postOfficeBoxPostalCode;
        this.postOfficeBoxCity = postOfficeBoxCity;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.surrogate = surrogate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public String getPostOfficeBoxNumber() {
        return postOfficeBoxNumber;
    }

    public void setPostOfficeBoxNumber(String postOfficeBoxNumber) {
        this.postOfficeBoxNumber = postOfficeBoxNumber;
    }

    public String getPostOfficeBoxPostalCode() {
        return postOfficeBoxPostalCode;
    }

    public void setPostOfficeBoxPostalCode(String postOfficeBoxPostalCode) {
        this.postOfficeBoxPostalCode = postOfficeBoxPostalCode;
    }

    public String getPostOfficeBoxCity() {
        return postOfficeBoxCity;
    }

    public void setPostOfficeBoxCity(String postOfficeBoxCity) {
        this.postOfficeBoxCity = postOfficeBoxCity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isSurrogate() {
        return surrogate;
    }

    public void setSurrogate(boolean surrogate) {
        this.surrogate = surrogate;
    }

    public static CompanyDto toDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName(company.getName());
        companyDto.setStreet(company.getStreet());
        companyDto.setHouseNumber(company.getHouseNumber());
        companyDto.setPostalCode(company.getPostalCode());
        companyDto.setCity(company.getCity());
        companyDto.setCountryIsoCode(company.getCountryIsoCode());
        companyDto.setPostOfficeBoxNumber(company.getPostOfficeBoxNumber());
        companyDto.setPostOfficeBoxPostalCode(company.getPostOfficeBoxPostalCode());
        companyDto.setPostOfficeBoxCity(company.getPostOfficeBoxCity());
        companyDto.setPhone(company.getPhone());
        companyDto.setEmail(company.getEmail());
        companyDto.setWebsite(company.getWebsite());
        companyDto.setSurrogate(company.isSurrogate());
        return companyDto;
    }
}
