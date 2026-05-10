package com.autoloc.service;

import com.autoloc.dto.ReservationRequest;
import com.autoloc.dto.ReservationResponse;
import com.autoloc.enums.statutReservation;
import com.autoloc.model.Client;
import com.autoloc.model.Reservation;
import com.autoloc.model.Voiture;
import com.autoloc.repository.ClientRepository;
import com.autoloc.repository.ReservationRepository;
import com.autoloc.repository.VehiculeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock private ReservationRepository reservationRepository;
    @Mock private ClientRepository clientRepository;
    @Mock private VehiculeRepository vehiculeRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Client client;
    private Voiture voiture;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setId(1L);
        client.setFirstname("Sophie");
        client.setLastname("Martin");

        voiture = new Voiture();
        voiture.setId(1L);
        voiture.setMarque("Peugeot");
        voiture.setModele("308");
        voiture.setPrixParJour(55.0);
        voiture.setOptions(new ArrayList<>());

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setClient(client);
        reservation.setVehicule(voiture);
        reservation.setDateDebut(LocalDate.of(2025, 6, 1));
        reservation.setDateFin(LocalDate.of(2025, 6, 5));
        reservation.setMontant(220.0);
        reservation.setDateCreation(LocalDate.now());
        reservation.setStatutReservation(statutReservation.EN_ATTENTE);
    }

    // ─── createReservation ────────────────────────────────────────────────

    @Test
    void createReservation_success() {
        ReservationRequest request = new ReservationRequest();
        request.setVehiculeId(1L);
        request.setDateDebut(LocalDate.of(2025, 6, 1));
        request.setDateFin(LocalDate.of(2025, 6, 5));

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(vehiculeRepository.findById(1L)).thenReturn(Optional.of(voiture));
        when(reservationRepository.existsByVehiculeIdAndDateRange(any(), any(), any()))
                .thenReturn(false);
        when(reservationRepository.save(any())).thenReturn(reservation);

        ReservationResponse response = reservationService.createReservation(1L, request);

        assertThat(response).isNotNull();
        assertThat(response.getStatut()).isEqualTo(statutReservation.EN_ATTENTE);
        assertThat(response.getMontant()).isEqualTo(220.0);
    }

    @Test
    void createReservation_vehiculeDejaReserve_throwsException() {
        ReservationRequest request = new ReservationRequest();
        request.setVehiculeId(1L);
        request.setDateDebut(LocalDate.of(2025, 6, 1));
        request.setDateFin(LocalDate.of(2025, 6, 5));

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(vehiculeRepository.findById(1L)).thenReturn(Optional.of(voiture));
        when(reservationRepository.existsByVehiculeIdAndDateRange(any(), any(), any()))
                .thenReturn(true);

        assertThatThrownBy(() -> reservationService.createReservation(1L, request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("déjà réservé");
    }

    // ─── valider ──────────────────────────────────────────────────────────

    @Test
    void valider_success() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(any())).thenReturn(reservation);

        ReservationResponse response = reservationService.valider(1L);

        assertThat(response.getStatut()).isEqualTo(statutReservation.CONFIRMEE);
    }

    @Test
    void valider_reservationDejaConfirmee_throwsException() {
        reservation.setStatutReservation(statutReservation.CONFIRMEE);
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        assertThatThrownBy(() -> reservationService.valider(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("EN_ATTENTE");
    }

    // ─── refuser ──────────────────────────────────────────────────────────

    @Test
    void refuser_success() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(any())).thenReturn(reservation);

        ReservationResponse response = reservationService.refuser(1L);

        assertThat(response.getStatut()).isEqualTo(statutReservation.REFUSEE);
    }

    // ─── findAll ──────────────────────────────────────────────────────────

    @Test
    void findAll_returnsAllReservations() {
        when(reservationRepository.findAll()).thenReturn(List.of(reservation));

        List<ReservationResponse> responses = reservationService.findAll();

        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getMontant()).isEqualTo(220.0);
    }

    // ─── getReservationsByClient ──────────────────────────────────────────

    @Test
    void getReservationsByClient_success() {
        when(reservationRepository.findByClientId(1L)).thenReturn(List.of(reservation));

        List<ReservationResponse> responses = reservationService.getReservationsByClient(1L);

        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getClientId()).isEqualTo(1L);
    }
}