import { AfterContentInit, Component, OnInit } from '@angular/core';
import { Account } from '../../../../model/account';
import { AccountService } from '../../../../service/account.service';
import { Icon } from '../../../../model/icon';
import { IconService } from '../../../../service/icon.service';

@Component({
  selector: 'app-manage-account',
  templateUrl: './manage-account.component.html',
  styleUrl: './manage-account.component.css'
})
export class ManageAccountComponent implements OnInit{
  accounts!:Account[];
  constructor(private accountService:AccountService, private iconService:IconService) {}


  ngOnInit(): void {
    this.accountService.getAllAccounts().subscribe(accounts => {
      this.accounts = accounts.accounts
      // console.log(accounts)
    })
  }

  deleteAccount(id:number) {
    this.accountService.deleteAccount(id).subscribe();
    for(let i = 0; i < this.accounts.length; i++) {
      if(this.accounts[i].id == id) {
        this.accounts.splice(i, 1);
        break;
      }
    }
  }

}
