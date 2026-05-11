package com.autoloc.service;

import com.autoloc.dto.PaiementRequest;
import com.autoloc.dto.PaiementResponse;
import com.autoloc.enums.modePaiement;
import com.autoloc.enums.statutPaiement;
import com.autoloc.enums.statutReservation;
import com.autoloc.model.Paiement;
import com.autoloc.model.Reservation;
import com.autoloc.repository.PaiementRepository;
import com.autoloc.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaiementServiceTest {

    @Mock private PaiementRepository paiementRepository;
    @Mock private ReservationRepository reservationRepository;

    @InjectMocks
    private PaiementService paiementService;

    private Reservation reservation;
    private Paiement paiement;

    @BeforeEach
    void setUp() {
        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setStatutReservation(statutReservation.CONFIRMEE);
        reservation.setMontant(220.0);

        paiement = new Paiement();
        paiement.setId(1L);
        paiement.setReservation(reservation);
        paiement.setMontant(220.0);
        paiement.setModePaiement(modePaiement.CB);
        paiement.setStatutPaiement(statutPaiement.CONFIRME);
        paiement.setDatePaiement(LocalDate.now());
    }

    // ─── effectuerPaiement ────────────────────────────────────────────────

    @Test
    void effectuerPaiement_success() {
        PaiementRequest request = new PaiementRequest();
        request.setMontant(220.0);
        request.setModePaiement(modePaiement.CB);

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(paiementRepository.findByReservationId(1L)).thenReturn(Optional.empty());
        when(paiementRepository.save(any())).thenReturn(paiement);

        PaiementResponse response = paiementService.effectuerPaiement(1L, request);

        assertThat(response).isNotNull();
        assertThat(response.getStatut()).isEqualTo(statutPaiement.CONFIRME);
        assertThat(response.getMontant()).isEqualTo(220.0);
    }

    @Test
    void effectuerPaiement_reservationNonConfirmee_throwsException() {
        reservation.setStatutReservation(statutReservation.EN_ATTENTE);

        PaiementRequest request = new PaiementRequest();
        request.setMontant(220.0);
        request.setModePaiement(modePaiement.CB);

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        assertThatThrownBy(() -> paiementService.effectuerPaiement(1L, request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("confirmée");
    }

    @Test
    void effectuerPaiement_dejaPayee_throwsException() {
        PaiementRequest request = new PaiementRequest();
        request.setMontant(220.0);
        request.setModePaiement(modePaiement.CB);

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(paiementRepository.findByReservationId(1L)).thenReturn(Optional.of(paiement));

        assertThatThrownBy(() -> paiementService.effectuerPaiement(1L, request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("déjà été payée");
    }

    // ─── rembourser ───────────────────────────────────────────────────────

    @Test
    void rembourser_success() {
        when(paiementRepository.findById(1L)).thenReturn(Optional.of(paiement));
        when(paiementRepository.save(any())).thenReturn(paiement);

        PaiementResponse response = paiementService.rembourser(1L);

        assertThat(response.getStatut()).isEqualTo(statutPaiement.REMBOURSE);
    }

    @Test
    void rembourser_paiementNonConfirme_throwsException() {
        paiement.setStatutPaiement(statutPaiement.EN_ATTENTE);
        when(paiementRepository.findById(1L)).thenReturn(Optional.of(paiement));

        assertThatThrownBy(() -> paiementService.rembourser(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("confirmé");
    }

    // ─── getPaiement ──────────────────────────────────────────────────────

    @Test
    void getPaiement_success() {
        when(paiementRepository.findById(1L)).thenReturn(Optional.of(paiement));

        PaiementResponse response = paiementService.getPaiement(1L);

        assertThat(response).isNotNull();
        assertThat(response.getMontant()).isEqualTo(220.0);
    }

    @Test
    void getPaiement_introuvable_throwsException() {
        when(paiementRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> paiementService.getPaiement(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Paiement introuvable");
    }
}