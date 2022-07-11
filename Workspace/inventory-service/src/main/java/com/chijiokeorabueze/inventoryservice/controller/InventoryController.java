package com.chijiokeorabueze.inventoryservice.controller;


import com.chijiokeorabueze.inventoryservice.dto.InventoryResponse;
import com.chijiokeorabueze.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;



    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }

//    @GetMapping("/{sku-code}")
//    @ResponseStatus(HttpStatus.OK)
//    public boolean isInStock(@PathVariable("sku-code") String skuCode) {
//        return inventoryService.isInStock(skuCode);
//    }
}
