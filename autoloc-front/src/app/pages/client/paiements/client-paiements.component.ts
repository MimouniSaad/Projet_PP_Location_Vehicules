import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { ReservationService } from '../../../core/services/reservation.service';
import { PaiementService } from '../../../core/services/paiement.service';
import { Reservation } from '../../../core/models/reservation.model';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive],
  selector: 'app-client-paiements',
  templateUrl: './client-paiements.component.html',
  styleUrls: ['./client-paiements.component.scss']
})
export class ClientPaiementsComponent implements OnInit {
  reservations: Reservation[] = [];
  loading = true;
  error = '';
  payingId: number | null = null;
  modePaiement = 'CB';
  successMsg = '';

  constructor(
    private reservationService: ReservationService,
    private paiementService: PaiementService
  ) {}

  ngOnInit(): void {
    this.reservationService.getMesReservations().subscribe({
      next: (data) => {
        this.reservations = data.filter(r => ['CONFIRMEE', 'TERMINEE'].includes(r.statut));
        this.loading = false;
      },
      error: () => { this.error = 'Erreur lors du chargement.'; this.loading = false; }
    });
  }

  ouvrirPaiement(r: Reservation): void {
    this.payingId = r.id;
    this.modePaiement = 'CB';
  }

  confirmerPaiement(r: Reservation): void {
    this.paiementService.payer(r.id, this.modePaiement).subscribe({
      next: () => {
        this.successMsg = `Paiement de ${r.montant}€ initié pour la réservation #${r.id}.`;
        this.payingId = null;
      },
      error: () => { this.error = 'Erreur lors du paiement.'; }
    });
  }

  annulerPaiement(): void { this.payingId = null; }

  getStatutLabel(s: string): string {
    const m: Record<string, string> = { CONFIRMEE: 'Confirmée', TERMINEE: 'Terminée' };
    return m[s] || s;
  }

  getStatutClass(s: string): string {
    return s === 'TERMINEE' ? 'badge-muted' : 'badge-success';
  }
}
