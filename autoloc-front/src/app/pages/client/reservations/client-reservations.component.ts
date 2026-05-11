import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { ReservationService } from '../../../core/services/reservation.service';
import { Reservation } from '../../../core/models/reservation.model';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive],
  selector: 'app-client-reservations',
  templateUrl: './client-reservations.component.html',
  styleUrls: ['./client-reservations.component.scss']
})
export class ClientReservationsComponent implements OnInit {
  reservations: Reservation[] = [];
  loading = true;
  error = '';

  constructor(private reservationService: ReservationService) {}

  ngOnInit(): void {
    this.reservationService.getMesReservations().subscribe({
      next: (data) => { this.reservations = data; this.loading = false; },
      error: () => { this.error = 'Erreur lors du chargement.'; this.loading = false; }
    });
  }

  annuler(r: Reservation): void {
    if (!confirm('Confirmer l\'annulation de cette réservation ?')) return;
    this.reservationService.annuler(r.id).subscribe({
      next: () => { this.reservations = this.reservations.filter(x => x.id !== r.id); },
      error: () => { this.error = 'Impossible d\'annuler cette réservation.'; }
    });
  }

  peutAnnuler(r: Reservation): boolean {
    return r.statut === 'EN_ATTENTE' || r.statut === 'CONFIRMEE';
  }

  getStatutClass(s: string): string {
    const m: Record<string, string> = {
      CONFIRMEE: 'badge-success', EN_ATTENTE: 'badge-warning',
      TERMINEE: 'badge-muted', REFUSEE: 'badge-danger', ANNULEE: 'badge-danger'
    };
    return m[s] || 'badge-muted';
  }

  getStatutLabel(s: string): string {
    const m: Record<string, string> = {
      CONFIRMEE: 'Confirmée', EN_ATTENTE: 'En attente',
      TERMINEE: 'Terminée', REFUSEE: 'Refusée', ANNULEE: 'Annulée'
    };
    return m[s] || s;
  }
}
