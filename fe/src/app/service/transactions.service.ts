import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from '../model/transaction';
import { formatDate } from '@angular/common';
import { FormatDateService } from './format-date.service';
import { StatisticResponse } from '../model/statisticResponse';
import { TransactionList } from '../model/transactionList';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {
  apiUrl = 'http://localhost:8080/api/transactions';
  constructor(private httpClient:HttpClient, private formatDate:FormatDateService) { }

  getAllTransaction():Observable<Transaction[]> {
    return this.httpClient.get<Transaction[]>(this.apiUrl);
  }

  getTransactionById(id:number):Observable<Transaction> {
    return this.httpClient.get<Transaction>(this.apiUrl+'/'+String(id));
  }

  getTransactionResponse(month:number, year:number):Observable<TransactionList[]> {
    console.log(this.apiUrl+'/home?month='+String(month)+'&year='+String(year))
    return this.httpClient.get<TransactionList[]>(this.apiUrl+'/home?month='+String(month)+'&year='+String(year));
  }

  getTransactionsByAccountId(id:number):Observable<Transaction[]> {
    return this.httpClient.get<Transaction[]>(this.apiUrl+'?accountId='+String(id));
  }

  getTransactionByAccountIdAndCreatedAt(id:number, month:number, year:number):Observable<Transaction[]> {
    console.log(this.apiUrl+'?accountId='+String(id)+'&month='+String(month+1)+'&year='+String(year));
    return this.httpClient.get<Transaction[]>(this.apiUrl+'?accountId='+String(id)+'&month='+String(month+1)+'&year='+String(year));
  }

  getStatisticResponse(month:number, year:number):Observable<StatisticResponse> {
    return this.httpClient.get<StatisticResponse>(this.apiUrl+'/statistic?'+'month='+String(month)+'&year='+String(year))
  }

  createTransaction(transaction:Transaction):Observable<Transaction> {
    console.log(transaction)
    return this.httpClient.post<Transaction>(this.apiUrl, transaction);
  }

  updateTransaction(transaction:Transaction):Observable<Transaction> {
    console.log(transaction);
    return this.httpClient.put<Transaction>(this.apiUrl+'/'+String(transaction.id), transaction);
  }

  deleteTransaction(id:number):Observable<void> {
    return this.httpClient.delete<void>(this.apiUrl+'/'+String(id))
  }
 }
