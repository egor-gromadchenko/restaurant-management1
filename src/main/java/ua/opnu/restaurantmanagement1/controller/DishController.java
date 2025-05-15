package ua.opnu.restaurantmanagement1.controller;

import ua.opnu.restaurantmanagement1.entity.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping("/{dishId}/assign-category/{categoryId}")
    public void assignCategory(@PathVariable Long dishId, @PathVariable Long categoryId) {
        service.assignCategory(dishId, categoryId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
