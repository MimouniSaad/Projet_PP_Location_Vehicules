import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { VehiculeService } from '../../../core/services/vehicule.service';
import { ReservationService } from '../../../core/services/reservation.service';
import { UserService } from '../../../core/services/user.service';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive],
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {
  etatParc = { disponibles: 0, loues: 0, maintenance: 0, horsService: 0 };
  reservationsAttente = 0;
  clientsActifs = 0;
  total = 0;

  constructor(
    private vehiculeService: VehiculeService,
    private reservationService: ReservationService,
    private userService: UserService,
    public auth: AuthService
  ) {}

  ngOnInit(): void {
    this.vehiculeService.getAll().subscribe({
      next: (v) => {
        this.etatParc.disponibles = v.filter(x => x.statut === 'DISPONIBLE').length;
        this.etatParc.loues       = v.filter(x => x.statut === 'LOUE').length;
        this.etatParc.maintenance = v.filter(x => x.statut === 'EN_MAINTENANCE').length;
        this.etatParc.horsService = v.filter(x => x.statut === 'HORS_SERVICE').length;
        this.total = v.length;
      },
      error: () => {}
    });

    this.reservationService.getAll().subscribe({
      next: (r) => { this.reservationsAttente = r.filter(x => x.statut === 'EN_ATTENTE').length; },
      error: () => {}
    });

    this.userService.getClients().subscribe({
      next: (c) => { this.clientsActifs = c.filter((x: any) => x.actif !== false).length; },
      error: () => {}
    });
  }

  get parcTotal(): number { return this.etatParc.disponibles + this.etatParc.loues + this.etatParc.maintenance + this.etatParc.horsService; }
  get parcPct(): (n: number) => number { return (n) => this.parcTotal ? Math.round((n / this.parcTotal) * 100) : 0; }
}
