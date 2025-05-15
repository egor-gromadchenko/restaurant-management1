package ua.opnu.restaurantmanagement1.service;

import ua.opnu.restaurantmanagement1.entity.DishCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.opnu.restaurantmanagement1.repository.DishCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishCategoryService {
    private final DishCategoryRepository categoryRepository;

    public List<DishCategory> getAll() {
        return categoryRepository.findAll();
    }

    public DishCategory getById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public DishCategory save(DishCategory category) {
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
