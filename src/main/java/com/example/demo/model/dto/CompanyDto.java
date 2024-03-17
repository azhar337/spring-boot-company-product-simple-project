package com.example.demo.model.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CompanyDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}
