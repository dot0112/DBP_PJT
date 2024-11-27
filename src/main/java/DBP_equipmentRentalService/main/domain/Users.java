package DBP_equipmentRentalService.main.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Users {
    @Id
    String userId;
    String passwd;
    String name;
    LocalDate dateOfBirth;
    String email;
    String phoneNumber;
    int rentalAvailability;
    LocalDate rentalAvailabilityDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRentalAvailability() {
        return rentalAvailability;
    }

    public void setRentalAvailability(int rentalAvailability) {
        this.rentalAvailability = rentalAvailability;
    }

    public LocalDate getRentalAvailabilityDate() {
        return rentalAvailabilityDate;
    }

    public void setRentalAvailabilityDate(LocalDate rentalAvailabilityDate) {
        this.rentalAvailabilityDate = rentalAvailabilityDate;
    }
}
