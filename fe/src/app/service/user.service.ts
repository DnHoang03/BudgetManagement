import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  apiUrl='http://localhost:8080/api/users'
  constructor(private httpClient:HttpClient) { }

  getUserByUsername(username:string):Observable<User> {
    return this.httpClient.get<User>(this.apiUrl+'?username='+String(username));
  }
}
