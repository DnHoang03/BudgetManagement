import { Component, OnInit } from '@angular/core';
import { Account } from '../../../../model/account';
import { TransactionsService } from '../../../../service/transactions.service';
import { AccountService } from '../../../../service/account.service';
import { ActivatedRoute } from '@angular/router';
import { Transaction } from '../../../../model/transaction';

@Component({
  selector: 'app-account-detail',
  templateUrl: './account-detail.component.html',
  styleUrl: './account-detail.component.css'
})
export class AccountDetailComponent implements OnInit {
  account:Account = {
    note:'',
    name:'',
    currencyId:1,
    iconId:0,
    accountTypeId:1,
    amount:0,
    userId:1
  };
  isAmountSelected:boolean = false;
  pickedDate:Date = new Date();
  transactions!:Transaction[];
  constructor(private transactionService:TransactionsService,
    private accountService:AccountService,
    private activatedRoute:ActivatedRoute) {

  }
  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params=>{
        this.accountService.getAccountById(params["id"]).subscribe(account => {
          this.account = account;
          console.log(account);
        });
        this.transactionService.getTransactionsByAccountId(params["id"]).subscribe(transaction => {
          this.transactions = transaction;
        });
      }
    )
  } 

  bindVal($event:string) {
    this.account.amount = Number($event);
    this.isAmountSelected = false;
  }

  setAmount() {
    this.isAmountSelected = true;
  }

  getTransaction() {
    this.transactionService.getTransactionByAccountIdAndCreatedAt(this.account.id!, this.pickedDate.getMonth(), this.pickedDate.getFullYear()).subscribe(transactions => {
      this.transactions = transactions;
      console.log(transactions);
    })
  }
}
