package codes.recursive.controller;

import codes.recursive.domain.Order;
import codes.recursive.service.OrderService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import java.util.List;

@Controller("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Get("/")
    public HttpResponse<List<Order>> listOrders() {
        return HttpResponse.ok(
                orderService.listOrders()
        );
    }

    @Get("/{id}")
    public HttpResponse getOrder(Long id) {
        Order order = orderService.getOrderById(id);
        if( order != null ) {
            return HttpResponse.ok(
                    order
            );
        }
        return HttpResponse.notFound();

    }

    @Post("/")
    public HttpResponse<Order> newOrder(@Body Order order) {
        return HttpResponse.created(
                orderService.newOrder(order)
        );
    }

    @Put("/")
    public HttpResponse updateOrder(@Body Order order) {
        orderService.updateOrder(order);
        return HttpResponse.ok();
    }

}