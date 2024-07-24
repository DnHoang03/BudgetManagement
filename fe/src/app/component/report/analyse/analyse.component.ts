import { Component, OnInit } from '@angular/core';
import { StatisticResponse } from '../../../model/statisticResponse';
import { TransactionsService } from '../../../service/transactions.service';

@Component({
  selector: 'app-analyse',
  templateUrl: './analyse.component.html',
  styleUrl: './analyse.component.css'
})
export class AnalyseComponent implements OnInit{
  statisticResponse:StatisticResponse = {};
  date:Date = new Date();
  constructor(
    private transactionService:TransactionsService
  ){}
  ngOnInit(): void {
    this.transactionService.getStatisticResponse(this.date.getMonth()+1, this.date.getFullYear()).subscribe(
      response => {
        this.statisticResponse = response;
      }
    )
  }

  
}
