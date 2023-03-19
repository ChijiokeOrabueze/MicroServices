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
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{


    @Autowired
    private final ProductRepository productRepository;


    public ResponseConstructor<ProductResponse> createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        product = productRepository.save(product);
        ProductResponse response = ProductResponse.builder()
                .id(product.getId())
                .price(product.getPrice())
                .description(product.getDescription())
                .name(product.getName())
                .build();

        log.info("Product {} saved", product.getId());
        return new ResponseConstructor<>("Product created", 201, response);
    }


    public List<ProductResponse> getProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductResponse> productResponses = products.stream().map(this::mapProductToProductResponse).toList();

        return productResponses;

    }

    public ProductResponse getProduct(String productId) throws Exception {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new Exception();
        }

        Product product1 = product.get();
        return ProductResponse.builder()
                .name(product1.getName())
                .price(product1.getPrice())
                .description(product1.getDescription())
                .id(product1.getId())
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
