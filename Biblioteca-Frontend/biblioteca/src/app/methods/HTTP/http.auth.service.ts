
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  // Método para hacer una petición GET
  getData(url: string): Observable<any> {
    return this.http.get<any>(url);
  }
  
  // Método para hacer una petición POST
   postData(url: string, data: any): Observable<any> {
    return this.http.post<any>(url, data);
  }

  // Otros métodos para PUT, DELETE, etc.
}
