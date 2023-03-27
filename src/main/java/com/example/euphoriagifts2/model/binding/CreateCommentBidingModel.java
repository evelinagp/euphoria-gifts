package com.example.euphoriagifts2.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateCommentBidingModel {

    @NotBlank
    @Size(min = 10)
    private String message;

    public CreateCommentBidingModel() {
    }

    public String getMessage() {
        return message;
    }

    public CreateCommentBidingModel setMessage(String message) {
        this.message = message;
        return this;
    }
}
