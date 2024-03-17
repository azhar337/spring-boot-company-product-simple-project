package com.example.demo.model.dto.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductRequestInsert implements Serializable {
    private String title;
    private double price;
    private int quantity;
}
