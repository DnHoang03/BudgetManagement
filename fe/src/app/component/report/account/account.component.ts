import { Component, OnInit } from '@angular/core';
import { Account } from '../../../model/account';
import { AccountService } from '../../../service/account.service';
import { AccountResponse } from '../../../model/accountResponse';
import { AccountTypesService } from '../../../service/account-types.service';
import { AccountTypes } from '../../../model/accountTypes';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrl: './account.component.css'
})
export class AccountComponent implements OnInit{
  accountResponse:AccountResponse = {
    asset:0,
    total:0,
    debt:0,
    accounts:[]
  };

  selectedTypeId:number = 0;
  types!:AccountTypes[];


  constructor(private accountService:AccountService, private accountTypeService:AccountTypesService) {
  }
  ngOnInit(): void {
    this.accountService.getAllAccounts().subscribe(response => {
      this.accountResponse = response;
      console.log(response);
    })
    this.accountTypeService.getAllAccountTypes().subscribe(types => {
      this.types = types;
      this.types.push({id:0, name:"Tất cả"})
    })
  }

  setSelectedType() {
    console.log(this.selectedTypeId);
  }
  
}
