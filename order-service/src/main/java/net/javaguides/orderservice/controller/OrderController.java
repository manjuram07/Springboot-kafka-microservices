package net.javaguides.orderservice.controller;


import lombok.RequiredArgsConstructor;
import net.javaguides.orderservice.dto.OrderRequest;
import net.javaguides.orderservice.dto.OrderResponse;
import net.javaguides.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public OrderResponse placeOrder(@RequestBody OrderRequest request) {

        return service.placeOrder(request);
    }
}