package ua.opnu.restaurantmanagement1.controller;

import ua.opnu.restaurantmanagement1.entity.Waiter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.opnu.restaurantmanagement1.service.WaiterService;

import java.util.List;

@RestController
@RequestMapping("/api/waiters")
@RequiredArgsConstructor
public class WaiterController {
    private final WaiterService service;

    @GetMapping
    public List<Waiter> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Waiter create(@RequestBody Waiter w) {
        return service.save(w);
    }

    @PutMapping("/{id}")
    public Waiter update(@PathVariable Long id, @RequestBody Waiter w) {
        return service.update(id, w);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
