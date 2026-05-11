package com.autoloc.service;

import com.autoloc.dto.LoginRequest;
import com.autoloc.dto.JwtResponse;
import com.autoloc.enums.userRole;
import com.autoloc.model.Client;
import com.autoloc.repository.UserRepository;
import com.autoloc.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    // ─── Mocks — dépendances simulées ─────────────────────────────────────
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    // ─── Classe testée — injecte les mocks automatiquement ────────────────
    @InjectMocks
    private AuthService authService;

    // ─── Données de test ──────────────────────────────────────────────────
    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setId(1L);
        client.setFirstname("Sophie");
        client.setLastname("Martin");
        client.setEmail("sophie.martin@gmail.com");
        client.setPassword("$2a$10$hashedPassword");
        client.setRole(userRole.CLIENT);
        client.setActif(true);
    }

    // ─── TEST 1 : login réussi ─────────────────────────────────────────────
    @Test
    void login_success() {
        // GIVEN
        LoginRequest request = new LoginRequest();
        request.setEmail("sophie.martin@gmail.com");
        request.setPassword("password123");

        when(userRepository.findByEmail("sophie.martin@gmail.com"))
                .thenReturn(Optional.of(client));
        when(passwordEncoder.matches("password123", "$2a$10$hashedPassword"))
                .thenReturn(true);
        when(jwtUtil.generateToken(client))
                .thenReturn("fake.jwt.token");

        // WHEN
        JwtResponse response = authService.login(request);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("fake.jwt.token");
        assertThat(response.getRole()).isEqualTo(userRole.CLIENT);
        assertThat(response.getEmail()).isEqualTo("sophie.martin@gmail.com");
    }

    // ─── TEST 2 : login — utilisateur introuvable ──────────────────────────
    @Test
    void login_userNotFound_throwsException() {
        // GIVEN
        LoginRequest request = new LoginRequest();
        request.setEmail("inexistant@mail.com");
        request.setPassword("password123");

        when(userRepository.findByEmail("inexistant@mail.com"))
                .thenReturn(Optional.empty());

        // WHEN + THEN
        assertThatThrownBy(() -> authService.login(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User not found");
    }

    // ─── TEST 3 : login — mauvais mot de passe ─────────────────────────────
    @Test
    void login_wrongPassword_throwsException() {
        // GIVEN
        LoginRequest request = new LoginRequest();
        request.setEmail("sophie.martin@gmail.com");
        request.setPassword("mauvaisPassword");

        when(userRepository.findByEmail("sophie.martin@gmail.com"))
                .thenReturn(Optional.of(client));
        when(passwordEncoder.matches("mauvaisPassword", "$2a$10$hashedPassword"))
                .thenReturn(false);

        // WHEN + THEN
        assertThatThrownBy(() -> authService.login(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Wrong password");
    }
}