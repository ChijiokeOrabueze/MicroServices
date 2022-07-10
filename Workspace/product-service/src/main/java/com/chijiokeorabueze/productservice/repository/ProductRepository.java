package com.chijiokeorabueze.productservice.repository;

import com.chijiokeorabueze.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
