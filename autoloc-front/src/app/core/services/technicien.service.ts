import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

export interface TechnicienRequest {
  firstname: string;
  lastname: string;
  email: string;
  password: string;
  specialite: string;
  phone?: string;
}

@Injectable({ providedIn: 'root' })
export class TechnicienService {
  private apiUrl = '/api/techniciens';
  constructor(private http: HttpClient) {}

  getAll(): Observable<User[]> { return this.http.get<User[]>(this.apiUrl); }
  getById(id: number): Observable<User> { return this.http.get<User>(`${this.apiUrl}/${id}`); }
  getDisponibles(): Observable<User[]> { return this.http.get<User[]>(`${this.apiUrl}/disponibles`); }
  create(data: TechnicienRequest): Observable<User> { return this.http.post<User>(this.apiUrl, data); }
  update(id: number, data: TechnicienRequest): Observable<User> { return this.http.put<User>(`${this.apiUrl}/${id}`, data); }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.apiUrl}/${id}`); }
}
