import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Note } from '../model/note';

@Injectable({
  providedIn: 'root'
})
export class NotesService {

  apiUrl:string = 'http://localhost:8080/api/notes'
  constructor(private httpClient:HttpClient) { }

  createNote(note:Note):Observable<Note> {
    return this.httpClient.post<Note>(this.apiUrl, note);
  }

  getNotes():Observable<Note[]> {
    return this.httpClient.get<Note[]>(this.apiUrl);
  }

  // updateNote(note:Note):Observable<Note>{
  //   return this.httpClient.put<Note>(this.apiUrl)
  // }
}
