import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AccountTypes } from '../model/accountTypes';

@Injectable({
  providedIn: 'root'
})
export class AccountTypesService {

  apiUrl = 'http://localhost:8080/api/account-types'

  constructor(private httpClient:HttpClient) { }

  getAllAccountTypes():Observable<AccountTypes[]> {
    return this.httpClient.get<AccountTypes[]>(this.apiUrl);
  }
}
