package ua.opnu.restaurantmanagement1.repository;

import ua.opnu.restaurantmanagement1.entity.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaiterRepository extends JpaRepository<Waiter, Long> {
}
