package net.javaguides.orderservice.kafka;

import net.javaguides.basedomains.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    private final NewTopic topic;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderEvent event) {

        if (event == null) {
            LOGGER.warn("Order event is null. Message will not be sent.");
            return;
        }

        LOGGER.info("Order event => {}", event.toString());

        // create Message
//        Message<OrderEvent> message = MessageBuilder
//                .withPayload(event)
//                .setHeader(KafkaHeaders.TOPIC, topic.name())
//                .build();
//        kafkaTemplate.send(message);

        kafkaTemplate.send(topic.name(), event.getOrder().getOrderId(), event)
                .whenComplete((sendResult, ex) -> {
                    if (ex != null) {
                        LOGGER.error("Failed to send message: {}", ex.getMessage());
                    } else LOGGER.info("Order sent successfully [orderId={}] → partition={}, offset={}",
                            event.getOrder().getOrderId(),
                            sendResult.getRecordMetadata().partition(),
                            sendResult.getRecordMetadata().offset());
                });
    }
}
