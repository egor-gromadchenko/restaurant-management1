package ua.opnu.restaurantmanagement1.service;

import ua.opnu.restaurantmanagement1.entity.Waiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.opnu.restaurantmanagement1.repository.WaiterRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WaiterService {
    private final WaiterRepository waiterRepository;

    public List<Waiter> getAll() {
        return waiterRepository.findAll();
    }

    public Waiter getById(Long id) {
        return waiterRepository.findById(id).orElseThrow();
    }

    public Waiter save(Waiter waiter) {
        return waiterRepository.save(waiter);
    }

    public Waiter update(Long id, Waiter updated) {
        Waiter w = getById(id);
        w.setName(updated.getName());
        w.setShift(updated.getShift());
        return waiterRepository.save(w);
    }

    public void delete(Long id) {
        waiterRepository.deleteById(id);
    }
}
