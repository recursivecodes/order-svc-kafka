package codes.recursive.messaging;

import codes.recursive.domain.Order;
import codes.recursive.domain.Shipment;
import codes.recursive.domain.ShipmentStatus;
import codes.recursive.service.OrderService;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@KafkaListener(offsetReset = OffsetReset.LATEST)
public class ShipmentConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(ShipmentConsumer.class);

    private final OrderService orderService;

    public ShipmentConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @Topic("shipping-topic")
    public Single<Shipment> receive(
            @KafkaKey String key,
            Single<Shipment> shipmentFlowable) {

        return shipmentFlowable.doOnSuccess(shipment -> {
            LOG.info("Shipment message received!");
            LOG.info("Updating order shipment status...");
            Order order = this.orderService.getOrderById(shipment.getOrderId());
            order.setShipmentStatus(ShipmentStatus.SHIPPED);
            orderService.updateOrder(order);
            LOG.info("Order shipment status updated!");
        });
    }
}