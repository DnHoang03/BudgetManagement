import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FormatDateService {

  constructor() { }

  format(date:Date):string {
    return String(date.toISOString());
  }
}
