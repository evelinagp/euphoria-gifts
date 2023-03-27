package com.example.euphoriagifts2.model.binding;

import com.example.euphoriagifts2.model.entity.enums.PaymentMethodEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddressBindingModel {
    private String city;
    private String postalCode;
    private String street;

    private PaymentMethodEnum paymentMethod;

    public AddressBindingModel() {
    }

    @NotBlank(message = "City can not be empty field!")
    @Size(min = 3, max = 20, message = "City must be between 3 and 20 characters!")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @NotBlank(message = "Postal code can not be empty field!")
    @Size(min = 1, max = 10, message = "Postal code must be between 1 and 10 characters!")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @NotBlank(message = "Street can not be empty field!")
    @Size(min = 3, max = 30, message = "Street must be between 3 and 30 characters!")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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


