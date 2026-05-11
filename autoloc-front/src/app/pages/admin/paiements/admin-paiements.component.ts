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
  selector: 'app-admin-paiements',
  templateUrl: './admin-paiements.component.html',
  styleUrls: ['./admin-paiements.component.scss']
})
export class AdminPaiementsComponent implements OnInit {
  reservations: Reservation[] = [];
  loading = true;
  error = '';
  success = '';

  constructor(
    private reservationService: ReservationService,
    private paiementService: PaiementService
  ) {}

  ngOnInit(): void {
    this.reservationService.getAll().subscribe({
      next: (data) => {
        this.reservations = data.filter(r => ['CONFIRMEE', 'TERMINEE'].includes(r.statut));
        this.loading = false;
      },
      error: () => { this.error = 'Erreur lors du chargement.'; this.loading = false; }
    });
  }

  confirmerPaiement(r: Reservation): void {
    this.paiementService.getByReservation(r.id).subscribe({
      next: (p) => {
        this.paiementService.confirmer(p.id).subscribe({
          next: () => { this.success = `Paiement de la réservation #${r.id} confirmé.`; },
          error: () => { this.error = 'Erreur lors de la confirmation.'; }
        });
      },
      error: () => { this.error = `Aucun paiement trouvé pour la réservation #${r.id}.`; }
    });
  }

  rembourser(r: Reservation): void {
    if (!confirm(`Rembourser la réservation #${r.id} (${r.montant}€) ?`)) return;
    this.paiementService.getByReservation(r.id).subscribe({
      next: (p) => {
        this.paiementService.rembourser(p.id).subscribe({
          next: () => { this.success = `Remboursement de la réservation #${r.id} effectué.`; },
          error: () => { this.error = 'Erreur lors du remboursement.'; }
        });
      },
      error: () => { this.error = `Aucun paiement trouvé pour la réservation #${r.id}.`; }
    });
  }

  getStatutClass(s: string): string {
    const m: Record<string, string> = { CONFIRMEE: 'badge-success', TERMINEE: 'badge-muted' };
    return m[s] || 'badge-muted';
  }

  getStatutLabel(s: string): string {
    const m: Record<string, string> = { CONFIRMEE: 'Confirmée', TERMINEE: 'Terminée' };
    return m[s] || s;
  }
}
