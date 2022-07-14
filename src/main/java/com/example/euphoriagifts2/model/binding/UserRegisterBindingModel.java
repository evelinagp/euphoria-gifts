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

    private Set<RoleEntity> roles;

    public UserRegisterBindingModel() {
    }

    @Size(min = 5, max = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Size(min = 5, max = 20)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Positive
    @Max(value = 150, message = "The age must be between 1 and 150.")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @NotBlank
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Size(min = 3)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Size(min = 3)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
     //   return this;
    }
}
