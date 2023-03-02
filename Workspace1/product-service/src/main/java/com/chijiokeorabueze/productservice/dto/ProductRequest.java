package com.chijiokeorabueze.productservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "name cannot be empty")
    private BigDecimal price;
}
