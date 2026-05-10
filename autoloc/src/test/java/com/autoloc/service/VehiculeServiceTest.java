package com.autoloc.service;

import com.autoloc.dto.VehiculeResponse;
import com.autoloc.enums.statutVehicule;
import com.autoloc.exception.VehiculeNotFoundException;
import com.autoloc.model.Voiture;
import com.autoloc.repository.OptionRepository;
import com.autoloc.repository.VehiculeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehiculeServiceTest {

    @Mock
    private VehiculeRepository vehiculeRepository;

    @Mock
    private OptionRepository optionRepository;

    @InjectMocks
    private VehiculeService vehiculeService;

    private Voiture voiture;

    @BeforeEach
    void setUp() {
        voiture = new Voiture();
        voiture.setId(1L);
        voiture.setMarque("Peugeot");
        voiture.setModele("308");
        voiture.setImmatriculation("AB-123-CD");
        voiture.setPrixParJour(55.0);
        voiture.setCaution(400.0);
        voiture.setAnnee(2021);
        voiture.setStatut(statutVehicule.DISPONIBLE);
        voiture.setOptions(new ArrayList<>());
    }

    @Test
    void findById_success() {
        when(vehiculeRepository.findById(1L))
                .thenReturn(Optional.of(voiture));

        VehiculeResponse response = vehiculeService.findById(1L);

        assertThat(response).isNotNull();
        assertThat(response.getMarque()).isEqualTo("Peugeot");
        assertThat(response.getModele()).isEqualTo("308");
        assertThat(response.getImmatriculation()).isEqualTo("AB-123-CD");
    }

    @Test
    void findById_notFound_throwsException() {
        when(vehiculeRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> vehiculeService.findById(99L))
                .isInstanceOf(VehiculeNotFoundException.class);
    }

    @Test
    void findAll_returnsAllVehicules() {
        when(vehiculeRepository.findAll())
                .thenReturn(List.of(voiture));

        List<VehiculeResponse> responses = vehiculeService.findAll();

        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getMarque()).isEqualTo("Peugeot");
    }

    @Test
    void supprimer_vehiculeLoue_throwsException() {
        voiture.setStatut(statutVehicule.LOUE);
        when(vehiculeRepository.findById(1L))
                .thenReturn(Optional.of(voiture));

        assertThatThrownBy(() -> vehiculeService.supprimer(1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("cours de location");
    }

    @Test
    void supprimer_vehiculeEnMaintenance_throwsException() {
        voiture.setStatut(statutVehicule.EN_MAINTENANCE);
        when(vehiculeRepository.findById(1L))
                .thenReturn(Optional.of(voiture));

        assertThatThrownBy(() -> vehiculeService.supprimer(1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("maintenance");
    }

    @Test
    void changerStatut_success() {
        when(vehiculeRepository.findById(1L))
                .thenReturn(Optional.of(voiture));
        when(vehiculeRepository.save(voiture))
                .thenReturn(voiture);

        VehiculeResponse response = vehiculeService
                .changerStatut(1L, statutVehicule.EN_MAINTENANCE);

        assertThat(response.getStatut()).isEqualTo(statutVehicule.EN_MAINTENANCE);
    }
}