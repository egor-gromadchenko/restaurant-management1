package ua.opnu.restaurantmanagement1.repository;

import ua.opnu.restaurantmanagement1.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
