package com.chijiokeorabueze.orderservice.repository;

import com.chijiokeorabueze.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
