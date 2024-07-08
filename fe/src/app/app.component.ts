import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  
  state:string = '';

  constructor(private router:Router) {}

  ngOnInit(): void {
    this.router.events.subscribe(event => {
      if(event instanceof NavigationEnd) {
        this.state = event.url.split('/')[1];
      }
    })
  }
  setState(state:string) {
    this.state = state;
  }
}
