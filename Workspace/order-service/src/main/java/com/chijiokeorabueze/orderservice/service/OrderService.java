package com.chijiokeorabueze.orderservice.service;

import com.chijiokeorabueze.orderservice.dto.InventoryResponse;
import com.chijiokeorabueze.orderservice.dto.OrderLineItemsDto;
import com.chijiokeorabueze.orderservice.dto.OrderRequest;
import com.chijiokeorabueze.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import com.chijiokeorabueze.orderservice.model.Order;
import com.chijiokeorabueze.orderservice.model.OrderLineItems;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems =  orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                        .map(OrderLineItems::getSkuCode)
                                .toList();

        InventoryResponse[] inventoryResponses = webClient.get()
                        .uri("http://localhost:8082/api/inventory",
                                uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                        .retrieve()
                        .bodyToMono(InventoryResponse[].class)
                        .block();

        boolean allProductInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

        if (allProductInStock) {
            orderRepository.save(order);
        }else { throw new IllegalArgumentException("Product is not in stock"); }


    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}
