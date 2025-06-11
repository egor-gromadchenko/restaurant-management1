package ua.opnu.restaurantmanagement1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.opnu.restaurantmanagement1.dto.OrderRequest;
import ua.opnu.restaurantmanagement1.entity.Customer;
import ua.opnu.restaurantmanagement1.entity.Dish;
import ua.opnu.restaurantmanagement1.entity.Order;
import ua.opnu.restaurantmanagement1.entity.Waiter;
import ua.opnu.restaurantmanagement1.repository.CustomerRepository;
import ua.opnu.restaurantmanagement1.repository.DishRepository;
import ua.opnu.restaurantmanagement1.repository.OrderRepository;
import ua.opnu.restaurantmanagement1.repository.WaiterRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final CustomerRepository customerRepository;
    private final WaiterRepository waiterRepository;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public List<Order> getByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> getByWaiter(Long waiterId) {
        return orderRepository.findByWaiterId(waiterId);
    }

    public Order create(OrderRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Клієнт не знайдений"));
        Waiter waiter = waiterRepository.findById(request.getWaiterId())
                .orElseThrow(() -> new RuntimeException("Офіціант не знайдений"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setWaiter(waiter);
        order.setOrderDate(LocalDateTime.now());
        order.setCompleted(false);

        return orderRepository.save(order);
    }

    public Order addDish(Long orderId, Long dishId) {
        Order order = getById(orderId);
        Dish dish = dishRepository.findById(dishId).orElseThrow();
        order.getItems().add(dish);
        return orderRepository.save(order);
    }

    public Order removeDish(Long orderId, Long dishId) {
        Order order = getById(orderId);
        order.getItems().removeIf(d -> d.getId().equals(dishId));
        return orderRepository.save(order);
    }

    public BigDecimal getTotal(Long orderId) {
        Order order = getById(orderId);
        return order.getItems().stream()
                .map(Dish::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Order complete(Long orderId) {
        Order order = getById(orderId);
        order.setCompleted(true);
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Object[]> getPopularDishes() {
        return orderRepository.findMostOrderedDishes();
    }
}
