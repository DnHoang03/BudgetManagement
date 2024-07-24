import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IconNote } from '../model/iconNote';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IconNoteService {
  apiUrl='http://localhost:8080/api/icon-notes'
  constructor(private httpClient:HttpClient) { }

  createIconNote(iconNote:IconNote):Observable<IconNote> {
    console.log(this.apiUrl)
    return this.httpClient.post<IconNote>(this.apiUrl, iconNote);
  }

  getIconNoteById(id:number):Observable<IconNote> {
    return this.httpClient.get<IconNote>(this.apiUrl+'/'+String(id));
  }

  getAllIconNoteByUsername(username:string):Observable<IconNote[]> {
    return this.httpClient.get<IconNote[]>(this.apiUrl+"?username="+String(username));
  }

  deleteIconNote(id:number):Observable<void> {
    return this.httpClient.delete<void>(this.apiUrl+'/'+String(id));
  }
}
