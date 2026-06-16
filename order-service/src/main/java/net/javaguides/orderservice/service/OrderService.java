package net.javaguides.orderservice.service;


import net.javaguides.orderservice.dto.OrderRequest;
import net.javaguides.orderservice.dto.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest request);
}
