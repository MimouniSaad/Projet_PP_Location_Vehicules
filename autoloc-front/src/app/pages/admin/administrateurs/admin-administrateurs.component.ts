import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive],
  selector: 'app-admin-administrateurs',
  templateUrl: './admin-administrateurs.component.html',
  styleUrls: ['./admin-administrateurs.component.scss']
})
export class AdminAdministrateursComponent {
  showModal = false;
  loading = false;
  success = '';
  error = '';

  newAdmin = { firstname: '', lastname: '', email: '', password: '' };

  constructor(public auth: AuthService) {}

  creer(): void {
    if (!this.newAdmin.firstname || !this.newAdmin.lastname || !this.newAdmin.email || !this.newAdmin.password) {
      this.error = 'Tous les champs sont obligatoires.';
      return;
    }
    this.loading = true;
    this.error = '';
    this.success = '';

    this.auth.createAdmin(this.newAdmin).subscribe({
      next: () => {
        this.success = `Compte administrateur créé pour ${this.newAdmin.email}.`;
        this.newAdmin = { firstname: '', lastname: '', email: '', password: '' };
        this.showModal = false;
        this.loading = false;
      },
      error: (err) => {
        this.error = err?.error || 'Erreur lors de la création du compte.';
        this.loading = false;
      }
    });
  }

  openModal(): void {
    this.error = '';
    this.success = '';
    this.newAdmin = { firstname: '', lastname: '', email: '', password: '' };
    this.showModal = true;
  }
}
