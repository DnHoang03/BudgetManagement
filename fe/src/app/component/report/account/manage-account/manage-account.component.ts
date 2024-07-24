import { Component, OnInit } from '@angular/core';
import { Account } from '../../../../model/account';
import { AccountService } from '../../../../service/account.service';
import { IconService } from '../../../../service/icon.service';
import { MatDialog } from '@angular/material/dialog';
import { DeleteComponent } from '../../../../share/dialog/delete/delete.component';

@Component({
  selector: 'app-manage-account',
  templateUrl: './manage-account.component.html',
  styleUrl: './manage-account.component.css'
})
export class ManageAccountComponent implements OnInit{
  accounts!:Account[];
  constructor(private accountService:AccountService
    , private iconService:IconService
    , private dialog:MatDialog) {}


  ngOnInit(): void {
    this.accountService.getAllAccounts().subscribe(accounts => {
      this.accounts = accounts.accounts
      // console.log(accounts)
    })
  }


  openDialog(id:number) {
    const dialogRef = this.dialog.open(DeleteComponent);
    dialogRef.afterClosed().subscribe(result => {
      if(result === 'confirm') {
        this.accountService.deleteAccount(id).subscribe();
        for(let i = 0; i < this.accounts.length; i++) {
          if(this.accounts[i].id == id) {
            this.accounts.splice(i, 1);
            break;
          }
        }
      }
    })
  }

}
