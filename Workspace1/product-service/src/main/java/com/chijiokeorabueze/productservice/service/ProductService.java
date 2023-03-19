package com.chijiokeorabueze.productservice.service;

import com.chijiokeorabueze.productservice.dto.ProductRequest;
import com.chijiokeorabueze.productservice.dto.ProductResponse;
import com.chijiokeorabueze.productservice.dto.ResponseConstructor;

import java.util.List;

public interface ProductService {

    <T> ResponseConstructor<T> createProduct (ProductRequest productRequest);

    List<ProductResponse> getProducts();

    ProductResponse getProduct(String productId) throws Exception;
}
