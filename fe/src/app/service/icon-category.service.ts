import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IconCategory } from '../model/iconCategory';

@Injectable({
  providedIn: 'root'
})
export class IconCategoryService {
  apiUrl='http://localhost:8080/api/icon-categories'
  constructor(private httpClient:HttpClient) { }

  getAllIconCategory():Observable<IconCategory[]> {
    return this.httpClient.get<IconCategory[]>(this.apiUrl)
  }
}
