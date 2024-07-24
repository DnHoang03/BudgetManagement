import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Currency } from '../model/currencies';
import { AccountService } from './account.service';
import { Account } from '../model/account';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {
  apiUrl = 'http://localhost:8080/api/currencies';
  constructor(private httpClient:HttpClient, private accountService:AccountService) { }

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
      total = Math.round(total*1.083);
    } else if(from == 'JPY') {
      total = Math.round(total*0.00622);
    } else {
      total = Math.round(total*0.00003934);
    }
    to = to.substring(0, 3);
    if(to == 'USD') {
      return total;
    } else if(to == 'EUR') {
      return Math.round(total*0.9233);
    } else if(to == 'JPY') {
      return Math.round(total*160.89);
    } else {
      return Math.round(total*25419.4204);
    }
  }
  calculateTotalReceive(transferId:number, receiveId:number, amount:number):number {
    let transferAccount:Account = {};
    let receiveAccount:Account = {};
    this.accountService.getAccountById(transferId).subscribe(account => {
      transferAccount = account;
    })
    this.accountService.getAccountById(receiveId).subscribe(account => { 
      receiveAccount = account;
    })
    return this.calculateTotal(amount, transferAccount.currencyName!, receiveAccount.currencyName!);
  }
}
