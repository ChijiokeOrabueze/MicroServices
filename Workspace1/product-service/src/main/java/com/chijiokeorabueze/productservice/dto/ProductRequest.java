package com.chijiokeorabueze.productservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 2, max = 200, message = "name should range between 2 to 200 characters.")
    private String name;

    @NotEmpty(message = "description cannot be empty")
    @Size(min = 2, max = 200, message = "name should range between 2 to 200 characters.")
    private String description;

    @NotNull(message = "name cannot be empty")
    private BigDecimal price;
}
