package ua.opnu.restaurantmanagement1.repository;

import ua.opnu.restaurantmanagement1.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByWaiterId(Long waiterId);

    @Query("SELECT d.name, COUNT(d) FROM Order o JOIN o.items d GROUP BY d.name ORDER BY COUNT(d) DESC")
    List<Object[]> findMostOrderedDishes();
}
