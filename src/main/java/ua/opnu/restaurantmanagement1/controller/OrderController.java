package ua.opnu.restaurantmanagement1.controller;

import ua.opnu.restaurantmanagement1.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.opnu.restaurantmanagement1.service.OrderService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @GetMapping
    public List<Order> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getByCustomer(@PathVariable Long customerId) {
        return service.getByCustomer(customerId);
    }

    @GetMapping("/waiter/{waiterId}")
    public List<Order> getByWaiter(@PathVariable Long waiterId) {
        return service.getByWaiter(waiterId);
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        return service.save(order);
    }

    @PutMapping("/{orderId}/add-dish/{dishId}")
    public Order addDish(@PathVariable Long orderId, @PathVariable Long dishId) {
        return service.addDish(orderId, dishId);
    }

    @PutMapping("/{orderId}/remove-dish/{dishId}")
    public Order removeDish(@PathVariable Long orderId, @PathVariable Long dishId) {
        return service.removeDish(orderId, dishId);
    }

    @GetMapping("/{orderId}/total")
    public BigDecimal getTotal(@PathVariable Long orderId) {
        return service.getTotal(orderId);
    }

    @PutMapping("/{orderId}/complete")
    public Order complete(@PathVariable Long orderId) {
        return service.complete(orderId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/popular-dishes")
    public List<Object[]> getPopularDishes() {
        return service.getPopularDishes();
    }
}
