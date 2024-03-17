package com.example.demo.model.dto.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductResultResponse implements Serializable {
    private String title;
    private double price;
    private int quantity;
}
