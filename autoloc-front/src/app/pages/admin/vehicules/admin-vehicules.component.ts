import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { VehiculeService } from '../../../core/services/vehicule.service';
import { Vehicule, VehiculeRequest } from '../../../core/models/vehicule.model';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive],
  selector: 'app-admin-vehicules',
  templateUrl: './admin-vehicules.component.html',
  styleUrls: ['./admin-vehicules.component.scss']
})
export class AdminVehiculesComponent implements OnInit {
  vehicules: Vehicule[] = [];
  filtered: Vehicule[] = [];
  keyword = '';
  showAddModal = false;
  showEditModal = false;
  showPanneModal = false;
  selected: Vehicule | null = null;
  newV: VehiculeRequest = { type:'VOITURE', marque:'', modele:'', immatriculation:'', annee: new Date().getFullYear(), prixParJour:0, caution:0, statut:'DISPONIBLE' };
  typePanne = '';
  descPanne = '';
  saving = false;
  errorMsg = '';
  successMsg = '';

  constructor(private vehiculeService: VehiculeService) {}

  ngOnInit(): void {
    this.vehiculeService.getAll().subscribe({ next: d => { this.vehicules = d; this.filtered = d; }, error: () => {} });
  }

  search(): void {
    this.filtered = this.vehicules.filter(v =>
      `${v.marque} ${v.modele} ${v.immatriculation}`.toLowerCase().includes(this.keyword.toLowerCase())
    );
  }

  openEdit(v: Vehicule): void {
    this.selected = v;
    this.newV = { type: v.type, marque: v.marque, modele: v.modele, immatriculation: v.immatriculation, annee: v.annee, prixParJour: v.prixParJour, caution: v.caution, statut: v.statut };
    this.showEditModal = true;
  }

  ajouter(): void {
    if (this.saving) return;
    this.saving = true;
    this.errorMsg = '';
    this.vehiculeService.create(this.newV).subscribe({
      next: (v) => {
        this.vehicules.push(v);
        this.filtered = [...this.vehicules];
        this.showAddModal = false;
        this.saving = false;
        this.newV = { type:'VOITURE', marque:'', modele:'', immatriculation:'', annee: new Date().getFullYear(), prixParJour:0, caution:0 };
      },
      error: (err) => {
        this.errorMsg = err?.error?.error || 'Erreur lors de l\'ajout.';
        this.saving = false;
      }
    });
  }

  modifier(): void {
    if (!this.selected || this.saving) return;
    this.saving = true;
    this.errorMsg = '';
    this.vehiculeService.update(this.selected.id, this.newV).subscribe({
      next: (v) => {
        const i = this.vehicules.findIndex(x => x.id === v.id);
        if (i >= 0) { this.vehicules[i] = v; this.filtered = [...this.vehicules]; }
        this.showEditModal = false;
        this.saving = false;
      },
      error: (err) => {
        this.errorMsg = err?.error?.error || 'Erreur lors de la modification.';
        this.saving = false;
      }
    });
  }

  supprimer(): void {
    if (!this.selected) return;
    this.vehiculeService.delete(this.selected.id).subscribe({
      next: () => {
        this.vehicules = this.vehicules.filter(v => v.id !== this.selected!.id);
        this.filtered = [...this.vehicules];
        this.showEditModal = false;
      },
      error: (err) => { this.errorMsg = err?.error?.error || 'Impossible de supprimer ce véhicule.'; }
    });
  }

  signalerPanne(): void {
    if (!this.selected) return;
    this.vehiculeService.updateStatut(this.selected.id, 'EN_MAINTENANCE').subscribe({
      next: () => {
        const v = this.vehicules.find(x => x.id === this.selected!.id);
        if (v) v.statut = 'EN_MAINTENANCE';
        this.showPanneModal = false;
      },
      error: () => {}
    });
  }

  getStatutClass(s: string): string {
    const m: Record<string,string> = { DISPONIBLE:'badge-success', LOUE:'badge-info', EN_MAINTENANCE:'badge-warning', HORS_SERVICE:'badge-danger' };
    return m[s] || 'badge-muted';
  }

  getStatutLabel(s: string): string {
    const m: Record<string,string> = { DISPONIBLE:'Disponible', LOUE:'Loué', EN_MAINTENANCE:'Maintenance', HORS_SERVICE:'Hors service' };
    return m[s] || s;
  }
}


