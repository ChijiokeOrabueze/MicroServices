package com.chijiokeorabueze.productservice.service;

import com.chijiokeorabueze.productservice.dto.ProductRequest;
import com.chijiokeorabueze.productservice.dto.ProductResponse;
import com.chijiokeorabueze.productservice.dto.ResponseConstructor;
import com.chijiokeorabueze.productservice.model.Product;
import com.chijiokeorabueze.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{


    @Autowired
    private final ProductRepository productRepository;


    public ResponseConstructor<Object> createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} saved", product.getId());
        return ResponseConstructor.builder()
                .message("Product created")
                .statusCode(201)
                .data(null)
                .build();
    }


    public ResponseConstructor<Object> getProduct() {
        List<Product> products = productRepository.findAll();

        List<ProductResponse> productResponses = products.stream().map(this::mapProductToProductResponse).toList();


        return ResponseConstructor
                .builder()
                .message("All products fetched.")
                .statusCode(200)
                .data(productResponses)
                .build();



    }

    private ProductResponse mapProductToProductResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }


}
