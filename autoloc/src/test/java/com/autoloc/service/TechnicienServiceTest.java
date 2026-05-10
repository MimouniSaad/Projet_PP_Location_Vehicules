package com.autoloc.service;

import com.autoloc.dto.TechnicienRequest;
import com.autoloc.dto.TechnicienResponse;
import com.autoloc.enums.statutMaintenance;
import com.autoloc.enums.userRole;
import com.autoloc.exception.TechnicienNotFoundException;
import com.autoloc.model.OrdreMaintenance;
import com.autoloc.model.Technicien;
import com.autoloc.repository.MaintenanceRepository;
import com.autoloc.repository.TechnicienRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TechnicienServiceTest {

    @Mock private TechnicienRepository technicienRepository;
    @Mock private MaintenanceRepository maintenanceRepository;
    @Mock private PasswordEncoder passwordEncoder;

    @InjectMocks
    private TechnicienService technicienService;

    private Technicien technicien;

    @BeforeEach
    void setUp() {
        technicien = new Technicien();
        technicien.setId(1L);
        technicien.setFirstname("Karim");
        technicien.setLastname("Mecano");
        technicien.setEmail("karim@autoloc.com");
        technicien.setPassword("$2a$10$hashed");
        technicien.setRole(userRole.Technicien);
        technicien.setActif(true);
        technicien.setSpecialite("Moteur");
        technicien.setDisponible(true);
        technicien.setOrdreMaintenances(new ArrayList<>());
    }

    // ─── creerTechnicien ──────────────────────────────────────────────────

    @Test
    void creerTechnicien_success() {
        TechnicienRequest request = new TechnicienRequest();
        request.setFirstname("Karim");
        request.setLastname("Mecano");
        request.setEmail("karim@autoloc.com");
        request.setPassword("password123");
        request.setSpecialite("Moteur");

        when(technicienRepository.findByEmail("karim@autoloc.com"))
                .thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("$2a$10$hashed");
        when(technicienRepository.save(any())).thenReturn(technicien);

        TechnicienResponse response = technicienService.creerTechnicien(request);

        assertThat(response).isNotNull();
        assertThat(response.getFirstname()).isEqualTo("Karim");
        assertThat(response.getSpecialite()).isEqualTo("Moteur");
    }

    @Test
    void creerTechnicien_emailDejaUtilise_throwsException() {
        TechnicienRequest request = new TechnicienRequest();
        request.setEmail("karim@autoloc.com");
        request.setPassword("password123");

        when(technicienRepository.findByEmail("karim@autoloc.com"))
                .thenReturn(Optional.of(technicien));

        assertThatThrownBy(() -> technicienService.creerTechnicien(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email déjà utilisé");
    }

    // ─── supprimerTechnicien ──────────────────────────────────────────────

    @Test
    void supprimerTechnicien_success() {
        when(technicienRepository.findById(1L)).thenReturn(Optional.of(technicien));

        technicienService.supprimerTechnicien(1L);

        verify(technicienRepository).delete(technicien);
    }

    @Test
    void supprimerTechnicien_avecOrdresEnCours_throwsException() {
        OrdreMaintenance ordre = new OrdreMaintenance();
        ordre.setStatut(statutMaintenance.EN_COURS);
        technicien.setOrdreMaintenances(List.of(ordre));

        when(technicienRepository.findById(1L)).thenReturn(Optional.of(technicien));

        assertThatThrownBy(() -> technicienService.supprimerTechnicien(1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("EN_COURS");
    }

    @Test
    void supprimerTechnicien_introuvable_throwsException() {
        when(technicienRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> technicienService.supprimerTechnicien(99L))
                .isInstanceOf(TechnicienNotFoundException.class);
    }

    // ─── findAll ──────────────────────────────────────────────────────────

    @Test
    void findAll_returnsAllTechniciens() {
        when(technicienRepository.findAll()).thenReturn(List.of(technicien));

        List<TechnicienResponse> responses = technicienService.findAll();

        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getEmail()).isEqualTo("karim@autoloc.com");
    }

    // ─── findDisponibles ──────────────────────────────────────────────────

    @Test
    void findDisponibles_returnsOnlyDisponibles() {
        when(technicienRepository.findByDisponibleTrue()).thenReturn(List.of(technicien));

        List<TechnicienResponse> responses = technicienService.findDisponibles();

        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getDisponible()).isTrue();
    }
}