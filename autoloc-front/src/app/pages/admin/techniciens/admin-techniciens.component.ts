import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TechnicienService, TechnicienRequest } from '../../../core/services/technicien.service';
import { User } from '../../../core/models/user.model';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule],
  selector: 'app-admin-techniciens',
  templateUrl: './admin-techniciens.component.html',
  styleUrls: ['./admin-techniciens.component.scss']
})
export class AdminTechniciensComponent implements OnInit {
  techniciens: User[] = [];
  filtered: User[] = [];
  keyword = '';
  showAddModal = false;
  showEditModal = false;
  selected: User | null = null;
  form: TechnicienRequest = { firstname: '', lastname: '', email: '', password: '', specialite: '', phone: '' };
  saving = false;
  errorMsg = '';

  constructor(private technicienService: TechnicienService) {}

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.technicienService.getAll().subscribe({
      next: d => { this.techniciens = d; this.filtered = d; },
      error: () => {}
    });
  }

  search(): void {
    const kw = this.keyword.toLowerCase();
    this.filtered = this.techniciens.filter(t =>
      `${t.firstname} ${t.lastname} ${t.email} ${t.specialite ?? ''}`.toLowerCase().includes(kw)
    );
  }

  openAdd(): void {
    this.form = { firstname: '', lastname: '', email: '', password: '', specialite: '', phone: '' };
    this.errorMsg = '';
    this.showAddModal = true;
  }

  openEdit(t: User): void {
    this.selected = t;
    this.form = {
      firstname: t.firstname,
      lastname: t.lastname,
      email: t.email,
      password: '',
      specialite: t.specialite ?? '',
      phone: t.phone ?? ''
    };
    this.errorMsg = '';
    this.showEditModal = true;
  }

  ajouter(): void {
    if (this.saving) return;
    this.saving = true;
    this.errorMsg = '';
    this.technicienService.create(this.form).subscribe({
      next: (t) => {
        this.techniciens.push(t);
        this.filtered = [...this.techniciens];
        this.showAddModal = false;
        this.saving = false;
      },
      error: (err) => {
        this.errorMsg = err?.error?.message || err?.error?.error || 'Erreur lors de la création.';
        this.saving = false;
      }
    });
  }

  modifier(): void {
    if (!this.selected || this.saving) return;
    this.saving = true;
    this.errorMsg = '';
    const payload: TechnicienRequest = { ...this.form };
    if (!payload.password) delete (payload as any).password;
    this.technicienService.update(this.selected.id, payload).subscribe({
      next: (t) => {
        const i = this.techniciens.findIndex(x => x.id === t.id);
        if (i >= 0) { this.techniciens[i] = t; this.filtered = [...this.techniciens]; }
        this.showEditModal = false;
        this.saving = false;
      },
      error: (err) => {
        this.errorMsg = err?.error?.message || err?.error?.error || 'Erreur lors de la modification.';
        this.saving = false;
      }
    });
  }

  supprimer(): void {
    if (!this.selected || this.saving) return;
    this.saving = true;
    this.technicienService.delete(this.selected.id).subscribe({
      next: () => {
        this.techniciens = this.techniciens.filter(t => t.id !== this.selected!.id);
        this.filtered = [...this.techniciens];
        this.showEditModal = false;
        this.saving = false;
      },
      error: (err) => {
        this.errorMsg = err?.error?.message || err?.error?.error || 'Impossible de supprimer ce technicien.';
        this.saving = false;
      }
    });
  }

  getInitials(t: User): string {
    return `${t.firstname?.[0] ?? ''}${t.lastname?.[0] ?? ''}`.toUpperCase();
  }
}
