package ua.opnu.restaurantmanagement1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.opnu.restaurantmanagement1.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
