package net.javaguides.basedomains.constants;

/**

 ORDER_CREATED_TOPIC
 ORDER_CANCELLED_TOPIC
 PAYMENT_SUCCESS_TOPIC
 PAYMENT_FAILED_TOPIC
 INVENTORY_UPDATED_TOPIC
 NOTIFICATION_TOPIC

*/

public class KafkaConstants {

    private KafkaConstants() {}

    public static final String ORDER_CREATED_TOPIC = "order-created";

    public static final String ORDER_CANCELLED_TOPIC = "order-cancelled";

    public static final String PAYMENT_SUCCESS_TOPIC = "payment-success";

    public static final String PAYMENT_FAILED_TOPIC = "payment-failed";

    public static final String INVENTORY_UPDATED_TOPIC = "inventory-updated";

    public static final String NOTIFICATION_TOPIC = "notification";
}