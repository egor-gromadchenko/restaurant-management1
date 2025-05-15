package ua.opnu.restaurantmanagement1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.opnu.restaurantmanagement1.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
