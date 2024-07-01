import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Currency } from '../model/currencies';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {
  apiUrl = 'http://localhost:8080/api/currencies';
  constructor(private httpClient:HttpClient) { }

  getAllCurrencies():Observable<Currency[]> {
    return this.httpClient.get<Currency[]>(this.apiUrl);
  }
}
