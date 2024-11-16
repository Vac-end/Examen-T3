import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environments';

@Injectable({
  providedIn: 'root'
})
export class ReclamoService {
  ruta = environment.API_URL + '/reclamos';  // Ajuste según la ruta en tu backend

  constructor(private http: HttpClient) {}

  // Obtener todos los reclamos
  getReclamos(): Observable<any> {
    return this.http.get(`${this.ruta}`);
  }

  // Crear un nuevo reclamo
  createReclamo(reclamo: any): Observable<any> {
    return this.http.post(`${this.ruta}`, reclamo);
  }

  getTiposDocumentos(): Observable<any> {
    return this.http.get<any>(`${this.ruta}/documento`);
  }

  // Obtener todos los departamentos
  getDepartamentos(): Observable<any> {
    return this.http.get<any>(`${this.ruta}/departamentos`);
  }

  // Obtener todas las provincias
  getProvincias(): Observable<any> {
    return this.http.get<any>(`${this.ruta}/provincias`);
  }

  // Obtener todos los distritos
  getDistritos(): Observable<any> {
    return this.http.get<any>(`${this.ruta}/distritos`);
  }

}
