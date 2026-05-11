import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { UserService } from '../../../core/services/user.service';
import { Client } from '../../../core/models/user.model';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive],
  selector: 'app-admin-clients',
  templateUrl: './admin-clients.component.html',
  styleUrls: ['./admin-clients.component.scss']
})
export class AdminClientsComponent implements OnInit {
  clients: Client[] = [];
  filtered: Client[] = [];
  search = '';
  loading = true;
  error = '';

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getClients().subscribe({
      next: (data) => { this.clients = data; this.filtered = data; this.loading = false; },
      error: () => { this.error = 'Erreur lors du chargement.'; this.loading = false; }
    });
  }

  filtrer(): void {
    const q = this.search.toLowerCase();
    this.filtered = this.clients.filter(c =>
      c.firstname.toLowerCase().includes(q) ||
      c.lastname.toLowerCase().includes(q) ||
      c.email.toLowerCase().includes(q)
    );
  }

  desactiver(c: Client): void {
    if (!confirm(`Désactiver le compte de ${c.firstname} ${c.lastname} ?`)) return;
    this.userService.desactiverClient(c.id).subscribe({
      next: () => { c.actif = false; },
      error: () => { this.error = 'Impossible de désactiver ce compte.'; }
    });
  }
}
