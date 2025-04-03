package com.inventory.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SellerUpdateDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Invalid email format"
    )
    private String email;

    //no @NotBlank - optional for updates
    private String password;

    @NotBlank
    private String companyName;

    private String companyDescription;
    private String contactPhone;
    private String businessAddress;
    private String taxId;
}