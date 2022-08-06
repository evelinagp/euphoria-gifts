package com.example.euphoriagifts2.model.binding;

import com.example.euphoriagifts2.model.entity.enums.PaymentMethodEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AddressBindingModel {
    private String city;
    private String postalCode;
    private String street;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private PaymentMethodEnum paymentMethod;

    public AddressBindingModel() {
    }

    @NotBlank(message = "This field can't be empty")
    @Size(min = 3, message = "The length of this field must be at least of 3 symbols")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotBlank(message = "This field can't be empty")
    @Size(min = 3, message = "The length of this field must be at least of 3 symbols")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Size(min = 3, message = "The length of this field must be at least of 3 symbols")
    @NotBlank(message = "This field can't be empty")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Size(min = 2, message = "The length of this field must be at least of 2 symbols")
    @NotBlank(message = "This field can't be empty")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Size(min = 5, message = "The length of this field must be at least of 5 symbols")
    @NotBlank(message = "This field can't be empty")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @NotBlank(message = "This field can't be empty")
    @Pattern(regexp = "^(\\w.+@\\w+)(.\\w+){2,}$", message = "The email must contains: lower and upper case letters, along with @ and dots")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "This field can't be empty")
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s./0-9]*$", message = "The phone number is not in the correct format")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NotNull(message = "Payment method can not be empty field!")
    @Enumerated(EnumType.STRING)
    public PaymentMethodEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}


