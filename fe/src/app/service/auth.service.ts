import { Injectable } from '@angular/core';
import { Login } from '../model/login';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AuthResponse } from '../model/authResponse';
import { jwtDecode } from 'jwt-decode'
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiUrl = "http://localhost:8080/api/auth"

  constructor(private httpClient: HttpClient, private router:Router) { }

  login(login: Login): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>(this.apiUrl + '/login', login);
  }

  isTokenExpired(): boolean {
    const token = localStorage.getItem('token');
    if (!token) return true;
    const decodedToken = jwtDecode(token);
    if (!decodedToken.exp) return true;
    const expirationDate = decodedToken.exp * 1000;
    const now = new Date().getTime();
    return (expirationDate < now);
  }

  logout() {
    localStorage.removeItem('token')
  } 

  refreshToken() {
    localStorage.removeItem('token')
    this.router.navigate(['/login'])
  }
}
