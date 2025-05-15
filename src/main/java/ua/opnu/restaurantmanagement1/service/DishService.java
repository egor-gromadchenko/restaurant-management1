package ua.opnu.restaurantmanagement1.service;

import ua.opnu.restaurantmanagement1.entity.Dish;
import ua.opnu.restaurantmanagement1.entity.DishCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.opnu.restaurantmanagement1.repository.DishCategoryRepository;
import ua.opnu.restaurantmanagement1.repository.DishRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final DishCategoryRepository categoryRepository;

    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    public Dish getById(Long id) {
        return dishRepository.findById(id).orElseThrow();
    }

    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish update(Long id, Dish updated) {
        Dish d = getById(id);
        d.setName(updated.getName());
        d.setPrice(updated.getPrice());
        return dishRepository.save(d);
    }

    public void assignCategory(Long dishId, Long categoryId) {
        Dish dish = getById(dishId);
        DishCategory category = categoryRepository.findById(categoryId).orElseThrow();
        dish.setCategory(category);
        dishRepository.save(dish);
    }

    public void delete(Long id) {
        dishRepository.deleteById(id);
    }
}
