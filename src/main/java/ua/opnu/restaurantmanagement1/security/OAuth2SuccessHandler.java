package ua.opnu.restaurantmanagement1.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import ua.opnu.restaurantmanagement1.entity.Role;
import ua.opnu.restaurantmanagement1.entity.User;
import ua.opnu.restaurantmanagement1.repository.UserRepository;
import ua.opnu.restaurantmanagement1.service.JwtService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        // Пошук або створення користувача
        User user = userRepository.findByUsername(email)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .username(email)
                            .role(Role.USER)  // OAuth-користувачам присвоюємо роль USER
                            .build();
                    return userRepository.save(newUser);
                });

        // Генерація JWT токена
        String token = jwtService.generateToken(user);

        // Редірект із токеном у параметрі (використовується для Postman або фронтенду)
        String redirectUrl = "https://restaurant-management-8wpo.onrender.com/oauth-success?token=" + token;

        response.sendRedirect(redirectUrl);
    }
}
