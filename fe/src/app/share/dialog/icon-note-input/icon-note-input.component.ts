import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AccountService } from '../../../service/account.service';
import { ResponseNumber } from '../../../model/responseNumber';
import { Account } from '../../../model/account';
import { SelectAccountComponent } from '../select-account/select-account.component';
import { ErrorPopupService } from '../../../service/error-popup.service';

@Component({
  selector: 'app-icon-note-input',
  templateUrl: './icon-note-input.component.html',
  styleUrl: './icon-note-input.component.css'
})
export class IconNoteInputComponent {
  numbers:(number|string)[] = [7, 8, 9, 'date', 4, 5, 6, '+', 1, 2, 3, '-', ',', 0, 'x', 'ok']
  current_value:string = '';
  pre_value:string = '';
  cal:string = '';
  dot:boolean = false;
  submit:boolean = false;
  note:string = '';
  accountTransfer:Account = {id:0}
  constructor(private dialogRef:MatDialogRef<IconNoteInputComponent>
    , private accountService:AccountService
    , private dialog:MatDialog
    , private errorPopupService: ErrorPopupService
  ){}

  evt_handle(num:(number|string)) {
    if(typeof num === 'number') {
      if(this.dot) {
        if(this.current_value.length-this.current_value.indexOf('.') == 3) {
          this.current_value = this.current_value.substring(0, this.current_value.length-1);
          this.current_value += String(num);
        } else {
          this.current_value += String(num);
        }
      } else {
        if(this.current_value.length>= 10) {
          this.current_value = this.current_value.substring(0, this.current_value.length-1);
        }
        this.current_value += String(num);
      }
    } else if(num == ',') {
      if(this.dot == false) {
        this.dot = true;
        this.current_value += '.';
      }
    } else if(num == '+') {
      if(this.cal == '+') {
        this.current_value = String(Number(this.pre_value)+Number(this.current_value));
        this.pre_value = this.current_value;
        this.current_value = '';
      } else if(this.cal == '-') {
        this.current_value = String(Number(this.pre_value)-Number(this.current_value));
        this.pre_value = this.current_value;
        this.current_value = '';
        this.cal = '+';
      } else {
        this.cal = '+';
        this.pre_value = this.current_value;
        this.current_value = '';
      }
    } else if(num == '-') {
      if(this.cal == '+') {
        this.current_value = String(Number(this.pre_value)+Number(this.current_value));
        this.pre_value = this.current_value;
        this.current_value = '';
        this.cal = '-';
      } else if(this.cal == '-') {
        this.current_value = String(Number(this.pre_value)-Number(this.current_value));
        this.pre_value = this.current_value;
        this.current_value = '';
      } else {
        this.cal = '-';
        this.pre_value = this.current_value;
        this.current_value = '';
      }
    } else if(num == 'ok') {
      if(this.cal == '+') {
        this.current_value = String(Number(this.pre_value)+Number(this.current_value));
        this.pre_value = '';
      } else if(this.cal == '-') {
        this.current_value = String(Number(this.pre_value)-Number(this.current_value));
        this.pre_value = '';
      } else {
        if(this.current_value == '')  {
          this.errorPopupService.showError("Nhập số lượng tiền!")
          return;
        }
        this.submit = true;
        let x:ResponseNumber = {
          note:this.note,
          amount:this.current_value,
          account:this.accountTransfer
        }
        this.dialogRef.close(x)
      }
      this.cal = '';
    } else if(num == 'x') {
      if(this.current_value.length) {
        this.current_value = this.current_value.substring(0, this.current_value.length-1);
      } else {
        if(this.pre_value.length) {
          this.current_value = this.pre_value;
          this.pre_value = '';
        }
        if(this.cal != '') {
          this.cal = '';
        }
      }
    }
    if(this.current_value.length-this.current_value.indexOf('.') > 3) {
      this.current_value = String(Math.round(Number(this.current_value)*100)/100);
    }
    if(this.current_value.indexOf('.') == -1) this.dot = false;
  }
  close() {
    this.dialogRef.close()
  }
  selectAccount() {
    const dialogRef = this.dialog.open(SelectAccountComponent, { width: '450px', height: '500px' ,data: { id: 0 }} );
    dialogRef.afterClosed().subscribe(result => {
      this.accountTransfer = result??this.accountTransfer
      console.log(result)
    })
  }
}
