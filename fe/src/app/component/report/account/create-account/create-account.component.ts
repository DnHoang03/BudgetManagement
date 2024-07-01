import { Component, OnInit } from '@angular/core';
import { AccountTypesService } from '../../../../service/account-types.service';
import { IconService } from '../../../../service/icon.service';
import { CurrencyService } from '../../../../service/currency.service';
import { Icon } from '../../../../model/icon';
import { AccountTypes } from '../../../../model/accountTypes';
import { Currency } from '../../../../model/currencies';
import { Account } from '../../../../model/account';
import { AccountService } from '../../../../service/account.service';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrl: './create-account.component.css'
})
export class CreateAccountComponent implements OnInit{
  icons!:Icon[];
  accountTypes!:AccountTypes[];
  currencies!:Currency[];
  note:string = '';
  accountName:string = '';
  selectedAccount!:Account;
  selectedCurrency!:Currency;
  selectedIcon!:Icon;
  amount!:number;
  constructor(
    private accountTypesService:AccountTypesService, 
    private iconService:IconService,
    private currencyService:CurrencyService,
    private accountService:AccountService ) {
      
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
  }
  
  createAccount():void {
    let account:Account = {
      name:this.accountName,
      amount:this.amount,
      note:this.note,
      userId:1,
      accountTypeId:this.selectedAccount.id??0,
      currencyId:this.selectedCurrency.id,
      iconId:this.selectedIcon.id
    }
    this.accountService.createAccount(account);
  }

}
