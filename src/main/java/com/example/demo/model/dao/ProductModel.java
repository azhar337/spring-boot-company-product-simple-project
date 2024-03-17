package com.example.demo.model.dao;

import java.util.UUID;

import javax.persistence.Column;

import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;


@Data
@Table(value = "product_tbl")
@Entity
public class ProductModel {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    //to be send to front end as reference
    @Column(name = "ref_no", nullable = false, unique = true) 
    private String refNo;

}
