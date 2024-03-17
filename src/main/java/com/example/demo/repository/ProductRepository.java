package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.dao.ProductModel;



@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
    ProductModel findByRefNo(String refNo);

    void deleteByRefNo(String refNo);
}
