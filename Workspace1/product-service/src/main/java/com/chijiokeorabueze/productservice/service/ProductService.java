package com.chijiokeorabueze.productservice.service;

import com.chijiokeorabueze.productservice.dto.ProductRequest;
import com.chijiokeorabueze.productservice.dto.ResponseConstructor;

public interface ProductService {

    ResponseConstructor<Object> createProduct (ProductRequest productRequest);

    ResponseConstructor<Object> getProduct();
}
