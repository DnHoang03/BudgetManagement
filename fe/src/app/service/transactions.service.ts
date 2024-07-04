import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from '../model/transaction';
import { formatDate } from '@angular/common';
import { FormatDateService } from './format-date.service';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {
  apiUrl = 'http://localhost:8080/api/transactions';
  constructor(private httpClient:HttpClient, private formatDate:FormatDateService) { }

  getAllTransaction():Observable<Transaction[]> {
    return this.httpClient.get<Transaction[]>(this.apiUrl);
  }

  getTransactionsByAccountId(id:number):Observable<Transaction[]> {
    return this.httpClient.get<Transaction[]>(this.apiUrl+'?accountId='+String(id));
  }

  getTransactionByAccountIdAndCreatedAt(id:number, month:number, year:number):Observable<Transaction[]> {
    console.log(month, year);
    return this.httpClient.get<Transaction[]>(this.apiUrl+'?accountId='+String(id)+'&month='+String(month+1)+'&year='+String(year));
  }

  createTransaction(transaction:Transaction):Observable<Transaction> {
    return this.httpClient.post<Transaction>(this.apiUrl, transaction);
  }
}
