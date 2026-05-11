import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User, Client } from '../models/user.model';

@Injectable({ providedIn: 'root' })
export class UserService {
  private apiUrl = '/api';
  constructor(private http: HttpClient) {}

  getClients(): Observable<Client[]> { return this.http.get<Client[]>(`${this.apiUrl}/clients`); }
  getTechniciens(): Observable<User[]> { return this.http.get<User[]>(`${this.apiUrl}/techniciens`); }
  getTechniciensDisponibles(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/techniciens/disponibles`);
  }
  desactiverClient(id: number): Observable<void> { return this.http.patch<void>(`${this.apiUrl}/clients/${id}/desactiver`, {}); }
}
