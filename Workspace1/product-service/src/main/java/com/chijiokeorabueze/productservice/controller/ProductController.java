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
    public ResponseEntity<ResponseConstructor<ProductResponse>> createProduct(@Valid @RequestBody ProductRequest productRequest) {

        ResponseConstructor<ProductResponse> createdProduct = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdProduct);


    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseConstructor<List<ProductResponse>> getProducts() {

        return new ResponseConstructor<>("Products fetched", 200, productService.getProducts());

    }

    @GetMapping("/{productId}")
    public ResponseConstructor<ProductResponse> getProduct(@Pattern(regexp = "^[a-fA-F0-9]{24}$") @PathVariable String productId) throws Exception {

        return new ResponseConstructor<>("Product fetched", 200, productService.getProduct(productId));

    }

}
