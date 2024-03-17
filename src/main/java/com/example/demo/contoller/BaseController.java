package com.example.demo.contoller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.Endpoint;
import com.example.demo.model.dto.request.ProductRequestInsert;
import com.example.demo.model.dto.response.ProductResponse;
import com.example.demo.model.dto.response.ProductResultResponse;
import com.example.demo.service.ProductService;
import com.example.demo.util.common.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping(Endpoint.DEMOPATH)
@Api(value = "Demo REST API")
public class BaseController {

    @Autowired
    private ProductService productService;


    @PostMapping("/add")
    @ApiOperation(value = "Add Product", response = Iterable.class)
    public BaseResponse<ProductResponse> addProduct(@RequestBody ProductRequestInsert entity) {
        return productService.insertData(entity);
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "Find Product By Id", response = Iterable.class)
    public BaseResponse<ProductResultResponse> getProduct(@PathVariable String id) {
        return productService.getAllData(id);
    }


    @PutMapping("update/{id}")
    @ApiOperation(value = "Update Product By Id", response = Iterable.class)
    public BaseResponse<ProductResponse> updateProduct(@PathVariable String id, @RequestBody ProductRequestInsert entity) {        
        return productService.updateData(id, entity);
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "Delete Product By Id", response = Iterable.class)
    public BaseResponse<ProductResponse> deleteProduct(@PathVariable String id) {
        return productService.deleteData(id);
    }
    
}
