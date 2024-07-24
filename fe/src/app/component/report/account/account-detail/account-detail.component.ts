import { AfterContentInit, Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Account } from '../../../../model/account';
import { TransactionsService } from '../../../../service/transactions.service';
import { AccountService } from '../../../../service/account.service';
import { ActivatedRoute } from '@angular/router';
import { TransactionList } from '../../../../model/transactionList';
import { CurrencyService } from '../../../../service/currency.service';

@Component({
  selector: 'app-account-detail',
  templateUrl: './account-detail.component.html',
  styleUrl: './account-detail.component.css'
})
export class AccountDetailComponent implements OnInit {
  account: Account = {};
  day:string[] = ['Chủ nhật', 'Thứ 2', 'Thứ 3', 'Thứ 4', 'Thứ 5', 'Thứ 6', 'Thứ 7' ];
  months:number[] = [];
  years:number[] = [];
  isAmountSelected: boolean = false;
  pickedDate: Date = new Date();
  currentMonth!:number;
  currenYear!:number;
  transactionList:TransactionList[] = [];
  constructor(private transactionService: TransactionsService,
    private accountService: AccountService,
    private activatedRoute: ActivatedRoute,
    private currencyService:CurrencyService) {

  }
  ngOnInit(): void {
    this.currentMonth = this.pickedDate.getMonth();
    this.currenYear = this.pickedDate.getFullYear();
    for(let i = 0; i < 12; i++) this.months.push(i);
    for(let i = 1970; i < 2100; i++) this.years.push(i);
    this.activatedRoute.params.subscribe(params => {
      this.accountService.getAccountById(params["id"]).subscribe(account => {
        this.account = account;
        console.log(account);
      });
      this.transactionService.getTransactionByAccountIdAndCreatedAt(params["id"], this.pickedDate.getMonth(), this.pickedDate.getFullYear()).subscribe(transaction => {
        let transactionList:TransactionList[] = [];
        for(let i = 31; i >= 1; i--) {
          let transList:TransactionList = {date:undefined,transactions:[]};
          for(let x of transaction) {
            if(this.getDate(x.createdAt!) == i) {
              if(transList.date == undefined) transList.date! = x.createdAt!;
              transList.transactions?.push(x);
            }
          }
          if(transList.date != undefined ) transactionList.push(transList);
        }
        this.transactionList = transactionList;
        console.log(transactionList)
      });
    }
    )
  }

  bindVal($event: string) {
    this.account.amount = Number($event);
    this.isAmountSelected = false;
  }

  setAmount() {
    this.isAmountSelected = true;
  }

  getTransaction() {
    this.transactionService.getTransactionByAccountIdAndCreatedAt(this.account.id!, this.currentMonth, this.currenYear).subscribe(transaction => {
      let transactionList:TransactionList[] = [];
        for(let i = 31; i >= 1; i--) {
          let transList:TransactionList = {date:undefined,transactions:[]};
          for(let x of transaction) {
            if(this.getDate(x.createdAt!) == i) {
              if(transList.date == undefined) transList.date! = x.createdAt!;
              transList.transactions?.push(x);
            }
          }
          if(transList.date != undefined ) transactionList.push(transList);
        }
        this.transactionList = transactionList;
        console.log(transactionList)
    })
  }

  getDay(date:Date) {
    return new Date(date).getDay();
  }

  getDate(date:Date) {
    return new Date(date).getDate();
  }

  getMonth(date:Date) {
    return new Date(date).getMonth()+1;
  }

}
