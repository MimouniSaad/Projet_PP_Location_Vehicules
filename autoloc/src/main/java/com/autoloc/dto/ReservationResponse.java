package com.autoloc.dto;

import com.autoloc.enums.statutReservation;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {
    private Long id;
    private Long clientId;
    private String clientNom;
    private Long vehiculeId;
    private String vehiculeMarque;
    private String vehiculeModele;
    private String immatriculation;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double montant;
    private double caution;
    private statutReservation statut;
    private LocalDate dateCreation;
    private LocalDate dateRetour;
}
