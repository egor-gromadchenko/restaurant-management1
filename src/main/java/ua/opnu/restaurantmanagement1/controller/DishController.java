package ua.opnu.restaurantmanagement1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.restaurantmanagement1.entity.Dish;
import ua.opnu.restaurantmanagement1.service.DishService;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService service;

    @GetMapping
    public List<Dish> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Dish create(@RequestBody Dish d) {
        return service.save(d);
    }

    @PutMapping("/{id}")
    public Dish update(@PathVariable Long id, @RequestBody Dish d) {
        return service.update(id, d);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/{dishId}/category/{categoryId}")
    public ResponseEntity<String> assignCategory(@PathVariable Long dishId, @PathVariable Long categoryId) {
        service.assignCategory(dishId, categoryId);
        return ResponseEntity.ok("Категорію призначено");
    }

    // ✅ ДОДАНО: Popular Dishes
    @GetMapping("/popular")
    public List<Object[]> getPopularDishes() {
        return service.getPopularDishes();
    }
}
