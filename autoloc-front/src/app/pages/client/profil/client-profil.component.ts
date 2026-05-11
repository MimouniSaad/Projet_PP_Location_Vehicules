import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive],
  selector: 'app-client-profil',
  templateUrl: './client-profil.component.html',
  styleUrls: ['./client-profil.component.scss']
})
export class ClientProfilComponent implements OnInit {
  form = { firstname: '', lastname: '', email: '', phone: '' };
  editMode = false;
  loading = false;
  success = '';
  error = '';

  constructor(public auth: AuthService, private http: HttpClient) {}

  ngOnInit(): void {
    const u = this.auth.currentUser;
    if (u) {
      this.form.firstname = u.firstname || '';
      this.form.lastname = u.lastname || '';
      this.form.email = u.email || '';
    }
  }

  sauvegarder(): void {
    this.loading = true;
    this.error = '';
    this.success = '';
    this.http.put('/api/auth/profil', this.form).subscribe({
      next: () => {
        this.success = 'Profil mis à jour avec succès.';
        this.editMode = false;
        this.loading = false;
      },
      error: () => {
        this.error = 'Erreur lors de la mise à jour.';
        this.loading = false;
      }
    });
  }

  annuler(): void {
    const u = this.auth.currentUser;
    if (u) {
      this.form.firstname = u.firstname || '';
      this.form.lastname = u.lastname || '';
      this.form.email = u.email || '';
    }
    this.editMode = false;
    this.error = '';
  }
}
