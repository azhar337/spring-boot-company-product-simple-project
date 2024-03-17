package com.example.demo.model.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class ProductDto {

    private UUID id;
    private String title;
    private double price;
    private int quantity;

}
