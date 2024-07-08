import { Component, OnInit } from '@angular/core';
import { IconNote } from '../../model/iconNote';
import { IconNoteService } from '../../service/icon-note.service';
import { Account } from '../../model/account';
import { AccountService } from '../../service/account.service';
import { MatDialog } from '@angular/material/dialog';
import { SelectAccountComponent } from '../../share/dialog/select-account/select-account.component';
import { CurrencyService } from '../../service/currency.service';
import { ErrorPopupService } from '../../service/error-popup.service';
import { Router } from '@angular/router';
import { TransactionsService } from '../../service/transactions.service';
import { IconNoteInputComponent } from '../../share/dialog/icon-note-input/icon-note-input.component';
import { Transaction } from '../../model/transaction';

@Component({
  selector: 'app-add-notes',
  templateUrl: './add-notes.component.html',
  styleUrl: './add-notes.component.css'
})
export class AddNotesComponent implements OnInit {
  iconNotes: IconNote[] = [];
  selectedAccount: Account = { id: 0 };
  selectedType: string = 'COST'
  selectedIconNote: IconNote = { id: 0 };
  transferAccount: Account = { id: 0 };
  receiveAccount: Account = { id: 0 };
  state: number = 1;
  totalTransfer: number = 0;
  totalReceive: number = 0;

  constructor(private iconNoteService: IconNoteService
    , private accountService: AccountService
    , private currencyService: CurrencyService
    , private dialog: MatDialog
    , private errorPopupService: ErrorPopupService
    , private router: Router
    , private transactionService: TransactionsService
  ) { }

  ngOnInit(): void {
    this.iconNoteService.getAllIconNote().subscribe(iconNotes => {
      this.iconNotes = iconNotes;
      console.log(iconNotes)
    })
  }

  setSelectedType(type: string) {
    this.selectedType = type;
    this.selectedIconNote = {id:0};
    this.transferAccount = {id:0};
    this.receiveAccount = {id:0};
  }

  setSelectedIconNote(iconNote: IconNote) {
    this.selectedIconNote = iconNote;
    const dialogRef = this.dialog.open(IconNoteInputComponent, { width: '450px', height: '500px'} );
    dialogRef.afterClosed().subscribe(result => {
      if(result !== undefined) {
        let transaction:Transaction = {};
        let amount = Number(result.amount)
        if(result.account !== undefined && result.account.id != 0) {
          transaction.transferAccountId = result.account.id;
          let account = result.account;
          if(this.selectedType == 'COST') {
            (account.amount < amount)?(this.errorPopupService.showError('Không đủ tiền!')):(account.amount-=amount)
          } else {
            account.amount += amount;
          }
          this.accountService.updateAccount(account).subscribe()
        }
        transaction.amount = amount;
        transaction.name = (result.note=='')?iconNote.name:result.note;
        transaction.type = this.selectedType;
        this.transactionService.createTransaction(transaction).subscribe()
      } 
    })
  }

  selectAccount(x: boolean) {
    const dialogRef = this.dialog.open(SelectAccountComponent, { width: '450px', height: '500px', data: { id: this.selectedAccount.id } });
    dialogRef.afterClosed().subscribe(result => {
      if (x === true) {
        this.transferAccount = result ?? this.transferAccount;
        if (this.transferAccount.id != 0) {
          this.currencyService.getCurrencyById(result.currencyId).subscribe(currency => {
            this.transferAccount.currencyName = currency.unit;
          })
        }
      } else {
        this.receiveAccount = result ?? this.receiveAccount;
        if (this.receiveAccount.id != 0) {
          this.currencyService.getCurrencyById(result.currencyId).subscribe(currency => {
            this.receiveAccount.currencyName = currency.unit;
          })
        }
      }
    })

  }

  bindVal($event: any) {
    this.totalReceive = Number($event);
    this.totalTransfer = this.currencyService.calculateTotal(this.totalReceive, this.receiveAccount.currencyName!, this.transferAccount.currencyName!);
    console.log(this.totalReceive, this.totalTransfer)
    if (this.totalTransfer > this.transferAccount.amount!) {
      this.errorPopupService.showError("Không đủ số tiền")
      this.totalReceive = this.totalTransfer = 0;
    } else {
      this.transferAccount.amount! -= this.totalTransfer;
      this.receiveAccount.amount! += this.totalReceive;
      this.accountService.updateAccount(this.transferAccount).subscribe()
      this.accountService.updateAccount(this.receiveAccount).subscribe()
      this.transactionService.createTransaction({
        type: 'TRANSFER',
        amount: this.totalTransfer,
        createdAt: new Date(),
        transferAccountId: this.transferAccount.id,
        receiveAccountId: this.receiveAccount.id
      }).subscribe()
      this.router.navigate(['/home'])
    }
  }
}
