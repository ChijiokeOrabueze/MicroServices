package com.chijiokeorabueze.productservice.controller;

import com.chijiokeorabueze.productservice.dto.ProductRequest;
import com.chijiokeorabueze.productservice.dto.ProductResponse;
import com.chijiokeorabueze.productservice.dto.ResponseConstructor;
import com.chijiokeorabueze.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseConstructor<Object> createProduct(@RequestBody ProductRequest productRequest) {

        return productService.createProduct(productRequest);


    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseConstructor<Object> getProducts() {

        return productService.getProduct();

    }

}
