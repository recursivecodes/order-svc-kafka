package codes.recursive.messaging;
import codes.recursive.domain.Order;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface OrderProducer {
    @Topic("order-topic")
    void sendMessage(@KafkaKey String key, Order order);
}