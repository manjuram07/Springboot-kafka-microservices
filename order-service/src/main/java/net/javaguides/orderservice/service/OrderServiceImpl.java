package net.javaguides.orderservice.service;


import lombok.RequiredArgsConstructor;
import net.javaguides.basedomains.dto.OrderCreatedEvent;
import net.javaguides.orderservice.Entity.Order;
import net.javaguides.orderservice.dto.OrderRequest;
import net.javaguides.orderservice.dto.OrderResponse;
import net.javaguides.orderservice.kafka.OrderProducer;
import net.javaguides.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderProducer producer;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {

        Order order = Order.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .customerEmail(request.getCustomerEmail())
                .status("CREATED")
                .build();

        Order savedOrder = repository.save(order);

        OrderCreatedEvent event = OrderCreatedEvent.builder()
                .orderId(savedOrder.getOrderId())
                .productId(savedOrder.getProductId())
                .quantity(savedOrder.getQuantity())
                .customerEmail(savedOrder.getCustomerEmail())
                .build();

        producer.sendOrderCreatedEvent(event);

        return OrderResponse.builder()
                .orderId(savedOrder.getOrderId())
                .message("Order placed successfully")
                .build();
    }
}