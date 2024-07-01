import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class IncomeService {
  apiUrl:string = 'http://localhost:8080/api/icons'
  constructor() { }
}
