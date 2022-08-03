package com.example.euphoriagifts2.model.service.validation;


import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ApiError {

    private HttpStatus status;
    private List<String> fieldWithErrors;


    public ApiError(HttpStatus status) {
        this.status = status;
        this.fieldWithErrors = new ArrayList<>();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void addFieldWithError(String error) {
        fieldWithErrors.add(error);
    }

    public List<String> getFieldsWithErrors() {
        return fieldWithErrors;
    }

}
