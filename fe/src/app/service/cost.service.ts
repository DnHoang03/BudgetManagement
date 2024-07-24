import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Icon } from '../model/icon';

@Injectable({
  providedIn: 'root'
})
export class CostService {

  constructor(private httpClient:HttpClient) { }


}
