package net.javaguides.orderservice.config;

import net.javaguides.basedomains.constants.KafkaConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderCreatedTopic() {

        return new NewTopic(
                KafkaConstants.ORDER_CREATED_TOPIC, //
                3,          // partitions
                (short) 1   // replication factor
        );
    }
}
