package ua.opnu.restaurantmanagement1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.opnu.restaurantmanagement1.dto.AuthResponse;
import ua.opnu.restaurantmanagement1.dto.LoginRequest;
import ua.opnu.restaurantmanagement1.dto.RegisterRequest;
import ua.opnu.restaurantmanagement1.entity.Role;
import ua.opnu.restaurantmanagement1.entity.User;
import ua.opnu.restaurantmanagement1.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(RegisterRequest request) {
        Role role = request.getRole() != null ? request.getRole() : Role.USER;

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Користувача не знайдено"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Невірний пароль");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
