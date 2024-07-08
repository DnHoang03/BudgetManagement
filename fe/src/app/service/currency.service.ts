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

  getCurrencyById(id:number):Observable<Currency> {
    return this.httpClient.get<Currency>(this.apiUrl+'/'+String(id))
  }

  calculateTotal(amount:number, from:string, to:string):number {
    from = from.substring(0, 3);
    let total:number = amount;
    if(from == 'USD') {
      total = amount;
    } else if(from == 'EUR') {
      total = total*1.083;
    } else if(from == 'JPY') {
      total = total*0.00622;
    } else {
      total = total*0.00003934;
    }
    to = to.substring(0, 3);
    if(to == 'USD') {
      return total;
    } else if(to == 'EUR') {
      return total*0.9233;
    } else if(to == 'JPY') {
      return total*160.89;
    } else {
      return total*25419.4204;
    }
  }
}
