import { Component, Input, OnInit } from '@angular/core';
import { AccountTypesService } from '../../../../service/account-types.service';
import { IconService } from '../../../../service/icon.service';
import { CurrencyService } from '../../../../service/currency.service';
import { Icon } from '../../../../model/icon';
import { AccountTypes } from '../../../../model/accountTypes';
import { Currency } from '../../../../model/currencies';
import { Account } from '../../../../model/account';
import { AccountService } from '../../../../service/account.service';
import { every } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrl: './create-account.component.css'
})
export class CreateAccountComponent implements OnInit{
  icons!:Icon[];
  accountTypes!:AccountTypes[];
  currencies!:Currency[];
  selectedInput:boolean = false;
  accountBind:Account = {
    note:'',
    name:'',
    currencyId:1,
    iconId:0,
    accountTypeId:1,
    amount:0,
    userId:1
  };
  constructor(
    private accountTypesService:AccountTypesService, 
    private iconService:IconService,
    private currencyService:CurrencyService,
    private accountService:AccountService,
    private activatedRoute: ActivatedRoute) {
      
    }
  ngOnInit(): void {
    this.iconService.getAllAccountIcon().subscribe(icons => {
      this.icons = icons;
    })
    this.accountTypesService.getAllAccountTypes().subscribe(accountTypes => {
      this.accountTypes = accountTypes;
    })
    this.currencyService.getAllCurrencies().subscribe(currencies => {
      this.currencies = currencies;
    })
    this.activatedRoute.params.subscribe(params=>{
      if(params["id"]){
        this.accountService.getAccountById(params["id"]).subscribe(account => {
          this.accountBind = account;
        });
      }
    })
    // this.accountBind = history.state;
  }
  
  createAccount():void {
    if(this.accountBind.id != undefined) {
      this.accountService.updateAccount(this.accountBind).subscribe(account => {
        console.log(account);
      });
    } else {
      this.accountService.createAccount(this.accountBind).subscribe(account => {
        console.log(account);
      });
    }
  }

  setSelectedInput() {
    this.selectedInput = true;
  }


  setSelectedIcon(icon:Icon) {
    this.accountBind.iconId = icon.id;
    console.log(icon.id);
  }

  setSelectedCurrency(currency:Currency) {
    this.accountBind.currencyId = currency.id;
    console.log(currency.id);
  }

  setSelectedAccountType(accountType:AccountTypes) {
    this.accountBind.accountTypeId = accountType.id;
  }

  bindVal($event:any) {
    this.accountBind.amount = Number($event);
    this.selectedInput = false;
  }
}
