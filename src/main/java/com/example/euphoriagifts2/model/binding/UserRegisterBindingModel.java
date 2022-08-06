package com.example.euphoriagifts2.model.binding;

import com.example.euphoriagifts2.model.entity.RoleEntity;

import javax.validation.constraints.*;
import java.util.Set;

public class UserRegisterBindingModel {
    private String username;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String password;
    private String confirmPassword;


    public UserRegisterBindingModel() {
    }

    @NotBlank(message = "Username can not be empty field!")
    @Size(min = 5, max = 20, message = "Username length must be between 5 and 20 characters!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotBlank(message = "First name can not be empty field!")
    @Size(min = 3, max = 20, message = "First name length must be between 3 and 20 characters!")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotBlank(message = "Last name can not be empty field!")
    @Size(min = 3, max = 20, message = "Last name length must be between 3 and 20 characters!")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Positive
    @Max(value = 150, message = "The age must be between 1 and 150.")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @NotBlank(message = "Email can not be empty field!")
    @Email(message = "Must be valid email!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "Password can not be empty field!")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank(message = "Password can not be empty field!")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters!")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
