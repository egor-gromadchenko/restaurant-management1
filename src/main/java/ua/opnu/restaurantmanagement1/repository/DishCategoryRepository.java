package ua.opnu.restaurantmanagement1.repository;

import ua.opnu.restaurantmanagement1.entity.DishCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishCategoryRepository extends JpaRepository<DishCategory, Long> {
}
