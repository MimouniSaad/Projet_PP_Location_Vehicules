import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { VehiculeService } from '../../../core/services/vehicule.service';
import { ReservationService } from '../../../core/services/reservation.service';
import { UserService } from '../../../core/services/user.service';
import { Reservation } from '../../../core/models/reservation.model';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive],
  selector: 'app-admin-rapports',
  templateUrl: './admin-rapports.component.html',
  styleUrls: ['./admin-rapports.component.scss']
})
export class AdminRapportsComponent implements OnInit {
  loading = true;

  totalVehicules = 0;
  disponibles = 0;
  loues = 0;
  enMaintenance = 0;

  totalReservations = 0;
  reservationsConfirmees = 0;
  reservationsTerminees = 0;
  reservationsAnnulees = 0;
  chiffreAffaires = 0;

  totalClients = 0;
  clientsActifs = 0;

  reservationsRecentes: Reservation[] = [];

  constructor(
    private vehiculeService: VehiculeService,
    private reservationService: ReservationService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    let done = 0;
    const check = () => { if (++done === 3) this.loading = false; };

    this.vehiculeService.getAll().subscribe({
      next: (v) => {
        this.totalVehicules = v.length;
        this.disponibles = v.filter(x => x.statut === 'DISPONIBLE').length;
        this.loues = v.filter(x => x.statut === 'LOUE').length;
        this.enMaintenance = v.filter(x => x.statut === 'EN_MAINTENANCE').length;
        check();
      },
      error: () => check()
    });

    this.reservationService.getAll().subscribe({
      next: (r) => {
        this.totalReservations = r.length;
        this.reservationsConfirmees = r.filter(x => x.statut === 'CONFIRMEE').length;
        this.reservationsTerminees = r.filter(x => x.statut === 'TERMINEE').length;
        this.reservationsAnnulees = r.filter(x => ['ANNULEE', 'REFUSEE'].includes(x.statut)).length;
        this.chiffreAffaires = r.filter(x => x.statut === 'TERMINEE').reduce((s, x) => s + x.montant, 0);
        this.reservationsRecentes = [...r].sort((a, b) => b.id - a.id).slice(0, 5);
        check();
      },
      error: () => check()
    });

    this.userService.getClients().subscribe({
      next: (c) => {
        this.totalClients = c.length;
        this.clientsActifs = c.filter(x => x.actif !== false).length;
        check();
      },
      error: () => check()
    });
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
