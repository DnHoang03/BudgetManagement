import { Component, Inject, inject, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { AccountService } from '../../../service/account.service';
import { Account } from '../../../model/account';
import { MAT_DIALOG_DATA} from '@angular/material/dialog';
@Component({
  selector: 'app-select-account',
  templateUrl: './select-account.component.html',
  styleUrl: './select-account.component.css'
})
export class SelectAccountComponent implements OnInit{

  accounts:Account[] = [];
  constructor(private dialogRef:MatDialogRef<SelectAccountComponent>
    , private accountService:AccountService
  , @Inject(MAT_DIALOG_DATA) public data: any){}
  ngOnInit(): void {
    this.accountService.getAllAccounts().subscribe(accounts => {
      this.accounts = accounts.accounts;
    })
  }
  selectAccount(account:Account) {
    this.dialogRef.close(account);
  }
  close() {
    this.dialogRef.close();
  }
}
