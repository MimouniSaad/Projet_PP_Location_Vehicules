import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { VehiculeService } from '../../../core/services/vehicule.service';
import { Vehicule } from '../../../core/models/vehicule.model';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {
  vehicules: Vehicule[] = [];
  loading = true;
  searchKeyword = '';
  filtreType: 'TOUS' | 'VOITURE' | 'CAMION' = 'TOUS';

  constructor(private vehiculeService: VehiculeService) {}

  ngOnInit(): void {
    this.vehiculeService.getAll().subscribe({
      next: (data) => {
        this.vehicules = data.filter(v => v.statut === 'DISPONIBLE');
        this.loading = false;
      },
      error: () => { this.loading = false; }
    });
  }

  get vehiculesFiltres(): Vehicule[] {
    return this.vehicules.filter(v => {
      const matchType = this.filtreType === 'TOUS' || v.type === this.filtreType;
      const kw = this.searchKeyword.toLowerCase();
      const matchSearch = !kw ||
        v.marque.toLowerCase().includes(kw) ||
        v.modele.toLowerCase().includes(kw);
      return matchType && matchSearch;
    });
  }

  setFiltre(type: 'TOUS' | 'VOITURE' | 'CAMION'): void {
    this.filtreType = type;
  }

  getTypeIcon(type: string): string {
    return type === 'CAMION' ? '🚛' : '🚗';
  }

  getBoiteLabel(b?: string): string {
    if (!b) return '';
    return b === 'AUTOMATIQUE' ? 'Automatique' : 'Manuelle';
  }
}
