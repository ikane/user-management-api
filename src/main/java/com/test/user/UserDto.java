package com.test.user;

import org.apache.commons.validator.routines.EmailValidator;

public record UserDto(Integer id, String firstname, String lastname, String email) {
    public UserDto {
        if (firstname == null || firstname.isBlank()) {
            throw new IllegalArgumentException("Firstname cannot be null or blank");
        }
        if (lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("Lastname cannot be null or blank");
        }
        if (email == null || email.isBlank() || !EmailValidator.getInstance().isValid(email)){
            throw new IllegalArgumentException("Email is invalid");
        }
    }
}
