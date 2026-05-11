package com.autoloc.service;

import com.autoloc.dto.MaintenanceRequest;
import com.autoloc.dto.MaintenanceResponse;
import com.autoloc.enums.statutMaintenance;
import com.autoloc.enums.statutVehicule;
import com.autoloc.exception.MaintenanceNotFoundException;
import com.autoloc.exception.TechnicienNotFoundException;
import com.autoloc.model.OrdreMaintenance;
import com.autoloc.model.Technicien;
import com.autoloc.model.Voiture;
import com.autoloc.repository.MaintenanceRepository;
import com.autoloc.repository.TechnicienRepository;
import com.autoloc.repository.VehiculeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MaintenanceServiceTest {

    @Mock private MaintenanceRepository maintenanceRepository;
    @Mock private VehiculeRepository vehiculeRepository;
    @Mock private TechnicienRepository technicienRepository;

    @InjectMocks
    private MaintenanceService maintenanceService;

    private Voiture voiture;
    private Technicien technicien;
    private OrdreMaintenance ordre;

    @BeforeEach
    void setUp() {
        voiture = new Voiture();
        voiture.setId(1L);
        voiture.setMarque("Renault");
        voiture.setModele("Clio");
        voiture.setImmatriculation("AB-123-CD");
        voiture.setStatut(statutVehicule.DISPONIBLE);
        voiture.setOptions(new ArrayList<>());

        technicien = new Technicien();
        technicien.setId(1L);
        technicien.setFirstname("Karim");
        technicien.setLastname("Mecano");
        technicien.setDisponible(true);
        technicien.setOrdreMaintenances(new ArrayList<>());

        ordre = new OrdreMaintenance();
        ordre.setId(1L);
        ordre.setVehicule(voiture);
        ordre.setImmatriculation("AB-123-CD");
        ordre.setTypeReparation("Moteur");
        ordre.setStatut(statutMaintenance.SIGNALE);
    }

    // ─── declencherMaintenance ────────────────────────────────────────────

    @Test
    void declencherMaintenance_success() {
        MaintenanceRequest request = new MaintenanceRequest();
        request.setVehiculeImmatriculation("AB-123-CD");
        request.setTypeReparation("Moteur");
        request.setDescription("Problème moteur");

        when(vehiculeRepository.findByImmatriculation("AB-123-CD"))
                .thenReturn(Optional.of(voiture));
        when(maintenanceRepository.save(any())).thenReturn(ordre);

        MaintenanceResponse response = maintenanceService.declencherMaintenance(request);

        assertThat(response).isNotNull();
        assertThat(response.getStatut()).isEqualTo(statutMaintenance.SIGNALE);
        assertThat(voiture.getStatut()).isEqualTo(statutVehicule.EN_MAINTENANCE);
    }

    // ─── assigner ─────────────────────────────────────────────────────────

    @Test
    void assigner_success() {
        when(maintenanceRepository.findById(1L)).thenReturn(Optional.of(ordre));
        when(technicienRepository.findById(1L)).thenReturn(Optional.of(technicien));
        when(maintenanceRepository.save(any())).thenReturn(ordre);

        MaintenanceResponse response = maintenanceService.assigner(1L, 1L);

        assertThat(response.getStatut()).isEqualTo(statutMaintenance.EN_COURS);
        assertThat(technicien.isDisponible()).isFalse();
    }

    @Test
    void assigner_technicienNonDisponible_throwsException() {
        technicien.setDisponible(false);
        when(maintenanceRepository.findById(1L)).thenReturn(Optional.of(ordre));
        when(technicienRepository.findById(1L)).thenReturn(Optional.of(technicien));

        assertThatThrownBy(() -> maintenanceService.assigner(1L, 1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("non disponible");
    }

    @Test
    void assigner_ordreNonSignale_throwsException() {
        ordre.setStatut(statutMaintenance.EN_COURS);
        when(maintenanceRepository.findById(1L)).thenReturn(Optional.of(ordre));

        assertThatThrownBy(() -> maintenanceService.assigner(1L, 1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("SIGNALE");
    }

    // ─── resoudre ─────────────────────────────────────────────────────────

    @Test
    void resoudre_success() {
        ordre.setStatut(statutMaintenance.EN_COURS);
        ordre.setTechnicien(technicien);

        when(maintenanceRepository.findById(1L)).thenReturn(Optional.of(ordre));
        when(maintenanceRepository.save(any())).thenReturn(ordre);

        MaintenanceResponse response = maintenanceService.resoudre(1L, 250.0);

        assertThat(response.getStatut()).isEqualTo(statutMaintenance.RESOLU);
        assertThat(voiture.getStatut()).isEqualTo(statutVehicule.DISPONIBLE);
        assertThat(technicien.isDisponible()).isTrue();
    }

    @Test
    void resoudre_ordreNonEnCours_throwsException() {
        ordre.setStatut(statutMaintenance.SIGNALE);
        when(maintenanceRepository.findById(1L)).thenReturn(Optional.of(ordre));

        assertThatThrownBy(() -> maintenanceService.resoudre(1L, 250.0))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("EN_COURS");
    }

    // ─── cloturerMaintenance ──────────────────────────────────────────────

    @Test
    void cloturerMaintenance_success() {
        ordre.setTechnicien(technicien);
        when(maintenanceRepository.findById(1L)).thenReturn(Optional.of(ordre));
        when(maintenanceRepository.save(any())).thenReturn(ordre);

        MaintenanceResponse response = maintenanceService.cloturerMaintenance(1L);

        assertThat(response.getStatut()).isEqualTo(statutMaintenance.ABANDONNE);
        assertThat(voiture.getStatut()).isEqualTo(statutVehicule.HORS_SERVICE);
        assertThat(technicien.isDisponible()).isTrue();
    }

    // ─── ordre introuvable ────────────────────────────────────────────────

    @Test
    void assigner_ordreIntrouvable_throwsException() {
        when(maintenanceRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> maintenanceService.assigner(99L, 1L))
                .isInstanceOf(MaintenanceNotFoundException.class);
    }
}