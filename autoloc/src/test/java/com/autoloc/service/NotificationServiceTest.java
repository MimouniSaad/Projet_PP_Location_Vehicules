package com.autoloc.service;

import com.autoloc.dto.NotificationRequest;
import com.autoloc.dto.NotificationResponse;
import com.autoloc.model.Notification;
import com.autoloc.model.User;
import com.autoloc.repository.NotificationRepository;
import com.autoloc.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock private NotificationRepository notificationRepository;
    @Mock private UserRepository userRepository;

    @InjectMocks
    private NotificationService notificationService;

    private User user;
    private Notification notification;

    @BeforeEach
    void setUp() {
        user = new User() {};
        user.setId(1L);
        user.setFirstname("Sophie");
        user.setLastname("Martin");

        notification = new Notification();
        notification.setId(1L);
        notification.setTitre("Test titre");
        notification.setMessage("Test message");
        notification.setUtilisateur(user);
        notification.setDateEnvoi(LocalDateTime.now());
    }

    // ─── envoyer ──────────────────────────────────────────────────────────

    @Test
    void envoyer_success() {
        NotificationRequest request = new NotificationRequest();
        request.setUserId(1L);
        request.setTitre("Test titre");
        request.setMessage("Test message");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(notificationRepository.save(any())).thenReturn(notification);

        NotificationResponse response = notificationService.envoyer(request);

        assertThat(response).isNotNull();
        assertThat(response.getTitre()).isEqualTo("Test titre");
        assertThat(response.getUserId()).isEqualTo(1L);
    }

    @Test
    void envoyer_utilisateurIntrouvable_throwsException() {
        NotificationRequest request = new NotificationRequest();
        request.setUserId(99L);
        request.setTitre("Test");
        request.setMessage("Message");

        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> notificationService.envoyer(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }

    // ─── findByUtilisateurId ──────────────────────────────────────────────

    @Test
    void findByUtilisateurId_returnsNotifications() {
        when(notificationRepository.findByUtilisateurIdOrderByDateEnvoiDesc(1L))
                .thenReturn(List.of(notification));

        List<NotificationResponse> responses =
                notificationService.findByUtilisateurId(1L);

        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getTitre()).isEqualTo("Test titre");
    }

    @Test
    void findByUtilisateurId_aucuneNotification_returnsEmpty() {
        when(notificationRepository.findByUtilisateurIdOrderByDateEnvoiDesc(99L))
                .thenReturn(List.of());

        List<NotificationResponse> responses =
                notificationService.findByUtilisateurId(99L);

        assertThat(responses).isEmpty();
    }
}