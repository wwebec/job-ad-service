package ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement;

import ch.admin.seco.jobs.services.jobadservice.core.domain.ValueObject;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.util.Objects;


@Embeddable
@Access(AccessType.FIELD)
public class Employer implements ValueObject<Employer> {
    private String name;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String countryIsoCode;

    protected Employer() {
        // For reflection libs
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

    @Override
    public String toString() {
        return "Employer{" +
                "name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", countryIsoCode='" + countryIsoCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer employer = (Employer) o;
        return Objects.equals(name, employer.name) &&
                Objects.equals(street, employer.street) &&
                Objects.equals(houseNumber, employer.houseNumber) &&
                Objects.equals(postalCode, employer.postalCode) &&
                Objects.equals(city, employer.city) &&
                Objects.equals(countryIsoCode, employer.countryIsoCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, street, houseNumber, postalCode, city, countryIsoCode);
    }
}
