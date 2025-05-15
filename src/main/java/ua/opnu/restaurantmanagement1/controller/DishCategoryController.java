package ua.opnu.restaurantmanagement1.controller;

import ua.opnu.restaurantmanagement1.entity.DishCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.opnu.restaurantmanagement1.service.DishCategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class DishCategoryController {
    private final DishCategoryService service;

    @GetMapping
    public List<DishCategory> getAll() {
        return service.getAll();
    }

    @PostMapping
    public DishCategory create(@RequestBody DishCategory c) {
        return service.save(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
