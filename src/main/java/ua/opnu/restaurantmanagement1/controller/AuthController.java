package ua.opnu.restaurantmanagement1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.restaurantmanagement1.dto.AuthResponse;
import ua.opnu.restaurantmanagement1.dto.LoginRequest;
import ua.opnu.restaurantmanagement1.dto.RegisterRequest;
import ua.opnu.restaurantmanagement1.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Користувача зареєстровано");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
