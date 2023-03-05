package com.chijiokeorabueze.productservice.controller;

import com.chijiokeorabueze.productservice.dto.ProductRequest;
import com.chijiokeorabueze.productservice.dto.ProductResponse;
import com.chijiokeorabueze.productservice.dto.ResponseConstructor;
import com.chijiokeorabueze.productservice.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseConstructor<Object>> createProduct(@Valid @RequestBody ProductRequest productRequest) {

        ResponseConstructor<Object> createdProduct = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdProduct);


    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseConstructor<Object> getProducts() {

        return productService.getProduct();

    }

    @GetMapping("/{code}")
    public ResponseEntity<Object> getProduct(@Pattern(regexp = "^RSV(-\\d{4,}){2}$") @PathVariable String code){

        return ResponseEntity.status(HttpStatus.OK).body("Hello");

    }

}
