package ua.opnu.restaurantmanagement1.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ua.opnu.restaurantmanagement1.entity.Role;
import ua.opnu.restaurantmanagement1.entity.User;
import ua.opnu.restaurantmanagement1.repository.UserRepository;
import ua.opnu.restaurantmanagement1.service.JwtService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        org.springframework.security.core.Authentication authentication) throws IOException, ServletException {

        // Отримуємо email із Google акаунту
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        // Якщо користувача не існує — створюємо
        User user = userRepository.findByUsername(email)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .username(email)
                            .role(Role.USER)  // OAuth-користувач отримує роль USER
                            .build();
                    return userRepository.save(newUser);
                });

        // Генерація JWT токена
        String token = jwtService.generateToken(user);

        // Установка автентифікації в SecurityContextHolder
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // Виводимо токен у вигляді JSON (працює для Postman)
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"token\": \"" + token + "\"}");
    }
}
