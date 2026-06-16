package net.javaguides.orderservice.kafka;


import lombok.RequiredArgsConstructor;
import net.javaguides.basedomains.constants.KafkaConstants;
import net.javaguides.basedomains.dto.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {

        kafkaTemplate.send(
                KafkaConstants.ORDER_CREATED_TOPIC,
                event.getOrderId().toString(), // key
                event
        );
        System.out.println("Event published successfully " + KafkaConstants.ORDER_CREATED_TOPIC + "\n"+event.getProductId()+","+event.getQuantity()+","+event.getCustomerEmail());
    }
}