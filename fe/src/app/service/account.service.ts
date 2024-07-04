import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Account } from '../model/account';
import { AccountTypesService } from './account-types.service';
import { AccountResponse } from '../model/accountResponse';
@Injectable({
  providedIn: 'root'
})
export class AccountService {
  apiUrl = 'http://localhost:8080/api/accounts';
  constructor(private httpClient:HttpClient) { }

  getAllAccounts(): Observable<AccountResponse> {
    return this.httpClient.get<AccountResponse>(this.apiUrl);
  }

  getAccountById(id:number): Observable<Account> {
    return this.httpClient.get<Account>(this.apiUrl+"/"+String(id));
  }

  createAccount(account:Account):Observable<Account> {
    return this.httpClient.post<Account>(this.apiUrl, account);
  }

  updateAccount(account:Account, id:number):Observable<Account> {
    return this.httpClient.put<Account>(this.apiUrl+"/"+String(id), account);
  }

  deleteAccount(id:number):Observable<void> {
    return this.httpClient.delete<void>(this.apiUrl+'/'+String(id));
  }
}
