import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Paiement } from '../models/paiement.model';

@Injectable({ providedIn: 'root' })
export class PaiementService {
  private apiUrl = '/api/paiements';
  constructor(private http: HttpClient) {}

  getByReservation(reservationId: number): Observable<Paiement> {
    return this.http.get<Paiement>(`${this.apiUrl}/reservation/${reservationId}`);
  }

  payer(reservationId: number, modePaiement: string): Observable<Paiement> {
    return this.http.post<Paiement>(`${this.apiUrl}/${reservationId}?modePaiement=${modePaiement}`, {});
  }

  confirmer(paiementId: number): Observable<Paiement> {
    return this.http.patch<Paiement>(`${this.apiUrl}/${paiementId}/confirmer`, {});
  }

  rembourser(paiementId: number): Observable<Paiement> {
    return this.http.patch<Paiement>(`${this.apiUrl}/${paiementId}/rembourser`, {});
  }
}
