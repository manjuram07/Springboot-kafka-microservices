package net.javaguides.stockservice.kafka;

import lombok.RequiredArgsConstructor;
import net.javaguides.basedomains.dto.OrderCreatedEvent;
import net.javaguides.stockservice.service.ProductService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final ProductService productService;

    @KafkaListener(
            topics = "order-created",
            groupId = "product-group")
    public void consume(OrderCreatedEvent event) {

        System.out.println("Received event : " + event);

        productService.reduceQuantity(event.getProductId(), event.getQuantity());
    }
}
