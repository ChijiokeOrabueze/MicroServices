package com.chijiokeorabueze.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseConstructor<ResponseDataType> {

    private String message;
    private Integer statusCode;
    private ResponseDataType data;
}
