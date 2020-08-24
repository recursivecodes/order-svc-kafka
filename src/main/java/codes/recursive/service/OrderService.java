package codes.recursive.service;

import codes.recursive.domain.Order;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class OrderService {
    public List<Order> orders = new ArrayList<>();

    public OrderService() {
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
        return order;
    }
}
