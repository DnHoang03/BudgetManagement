import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrl: './report.component.css'
})
export class ReportComponent implements OnInit {
  state:number = 1;
  constructor(private router:Router) {}
  ngOnInit(): void {
    (this.router.url.substring(8, 15) == 'analyse')?(this.state = 1):(this.state = 0)
  }
  setState() {
    console.log(this.router.url)
    this.state = 1-this.state;
  }
}
