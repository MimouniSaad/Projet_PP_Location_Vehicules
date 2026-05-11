import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reservation, ReservationRequest } from '../models/reservation.model';

@Injectable({ providedIn: 'root' })
export class ReservationService {
  private apiUrl = '/api/reservations';
  constructor(private http: HttpClient) {}

  getAll(): Observable<Reservation[]> { return this.http.get<Reservation[]>(this.apiUrl); }
  getById(id: number): Observable<Reservation> { return this.http.get<Reservation>(`${this.apiUrl}/${id}`); }
  getMesReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`${this.apiUrl}/mes-reservations`);
  }
  create(data: ReservationRequest): Observable<Reservation> { return this.http.post<Reservation>(this.apiUrl, data); }
  confirmer(id: number): Observable<Reservation> { return this.http.patch<Reservation>(`${this.apiUrl}/${id}/valider`, {}); }
  annuler(id: number): Observable<void> { return this.http.delete<void>(`${this.apiUrl}/${id}`); }
  refuser(id: number): Observable<Reservation> { return this.http.patch<Reservation>(`${this.apiUrl}/${id}/refuser`, {}); }
}
