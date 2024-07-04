import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Icon } from '../model/icon';

@Injectable({
  providedIn: 'root'
})
export class IconService {
  apiUrl:string = 'http://localhost:8080/api/icons'
  constructor(private httpClient:HttpClient) { }

  getAllAccountIcon(): Observable<Icon[]> {
    return this.httpClient.get<Icon[]>(this.apiUrl+"?type=ACCOUNT");
  }

  getAllNoteIcon():Observable<Icon[]> {
    return this.httpClient.get<Icon[]>(this.apiUrl+"?type=SPENDING");
  }

  getIconById(id:number):Observable<Icon> {
    return this.httpClient.get<Icon>(this.apiUrl+"/"+id.toString);
  }

}
