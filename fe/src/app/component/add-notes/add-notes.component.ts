import { Component, OnInit } from '@angular/core';
import { IconNote } from '../../model/iconNote';
import { IconNoteService } from '../../service/icon-note.service';
import { Account } from '../../model/account';
import { AccountService } from '../../service/account.service';
import { MatDialog } from '@angular/material/dialog';
import { SelectAccountComponent } from '../../share/dialog/select-account/select-account.component';

@Component({
  selector: 'app-add-notes',
  templateUrl: './add-notes.component.html',
  styleUrl: './add-notes.component.css'
})
export class AddNotesComponent implements OnInit{
  iconNotes:IconNote[] = [];
  selectedAccount:Account = {id:0};
  selectedType:string = 'COST'
  selectedIconNote:IconNote = {id:0};
  constructor(private iconNoteService:IconNoteService
    ,private accountService:AccountService
    ,private dialog:MatDialog
  ) {}

  ngOnInit(): void {
    this.iconNoteService.getAllIconNote().subscribe(iconNotes => {
      this.iconNotes = iconNotes;
      console.log(iconNotes)
    })
  }
  
  setSelectedType(type:string) {
    this.selectedType = type;
  }

  setSelectedIconNote(iconNote:IconNote) {
    this.selectedIconNote = iconNote;
  }

  test() {
    const dialogRef = this.dialog.open(SelectAccountComponent, {width: '450px', height:'500px', data:{id:this.selectedAccount.id}});
    dialogRef.afterClosed().subscribe(result => {
      this.selectedAccount = result??{id:0};
    })
  }
}
