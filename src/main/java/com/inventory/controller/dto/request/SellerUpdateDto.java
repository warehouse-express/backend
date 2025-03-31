package com.inventory.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SellerUpdateDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
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