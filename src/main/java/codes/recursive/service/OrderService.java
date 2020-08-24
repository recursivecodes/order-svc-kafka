package codes.recursive.service;

import codes.recursive.domain.Order;
import codes.recursive.messaging.OrderProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Singleton
public class OrderService {
    private final OrderProducer orderProducer;
    public List<Order> orders = new ArrayList<>();

    public OrderService(
            OrderProducer orderProducer
    ) {
        this.orderProducer = orderProducer;
    }

    public Order getOrderById(Long id) {
        return orders.stream().filter(it -> it.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Order> listOrders() {
        return orders;
    }

    public void updateOrder(Order order) {
        Order existingOrder = getOrderById(order.getId());
        int i = orders.indexOf(existingOrder);
        orders.set(i, order);
    }

    public Order newOrder(Order order) {
        order.setId((long) orders.size());
        this.orders.add(order);
        try {
            orderProducer.sendMessage(UUID.randomUUID().toString(), new ObjectMapper().writeValueAsString(order));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return order;
    }
}
