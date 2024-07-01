import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Account } from '../model/account';
import { AccountTypesService } from './account-types.service';
@Injectable({
  providedIn: 'root'
})
export class AccountService {
  apiUrl = 'http://localhost:8080/api/accounts';
  constructor(private httpClient:HttpClient) { }

  getAllAccounts(): Observable<Account[]> {
    return this.httpClient.get<Account[]>(this.apiUrl);
  }

  createAccount(account:Account):Observable<Account> {
    return this.httpClient.post<Account>(this.apiUrl, account);
  }
}
