import { Component, OnInit } from '@angular/core';
import { TransactionList } from '../../model/transactionList';
import { TransactionsService } from '../../service/transactions.service';
import { StatisticResponse } from '../../model/statisticResponse';
import { MatDialog } from '@angular/material/dialog';
import { DatePickerComponent } from '../../share/dialog/date-picker/date-picker.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  transactionLists:TransactionList[] = [];
  statisticResponse:StatisticResponse = {}
  date:Date = new Date();
  day:string[] = ['Chủ nhật', 'Thứ 2', 'Thứ 3', 'Thứ 4', 'Thứ 5', 'Thứ 6', 'Thứ 7' ];
  constructor(
    private transactionService:TransactionsService,
    private dialog:MatDialog
  ) {}
  ngOnInit(): void {
    this.transactionService.getTransactionResponse(this.date.getMonth()+1, this.date.getFullYear())
    .subscribe(response => {
      this.transactionLists = response;
      console.log(response);
    })
    this.transactionService.getStatisticResponse(this.date.getMonth()+1, this.date.getFullYear())
    .subscribe(response => {
      this.statisticResponse = response;
    })
  }

  setTransactionList() {
    this.transactionService.getTransactionResponse(this.date.getMonth()+1, this.date.getFullYear())
    .subscribe(response => {
      this.transactionLists = response;
      console.log(response);
    })
  }

  getDate(date:Date) {
    return new Date(date).getDate();
  }

  getMonth(date:Date) {
    return new Date(date).getMonth()+1;
  }

  getYear(date:Date) {
    return new Date(date).getFullYear();
  }

  getDay(date:Date) {
    return this.day[new Date(date).getDay()];
  }

  openDialog() {
    const dialogRef = this.dialog.open(DatePickerComponent, {data:this.date});
    dialogRef.afterClosed().subscribe(result => {
      if(result !== undefined) {
        this.date = result;
        this.setTransactionList()
      }
    })
  }

}
