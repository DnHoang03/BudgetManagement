import { Component, inject, OnInit } from '@angular/core';
import { IconNote } from '../../../model/iconNote';
import { IconNoteService } from '../../../service/icon-note.service';
import { MatDialog } from '@angular/material/dialog';
import { DeleteComponent } from '../../../share/dialog/delete/delete.component';
import { AuthService } from '../../../service/auth.service';

@Component({
  selector: 'app-setting-icon-note',
  templateUrl: './setting-icon-note.component.html',
  styleUrl: './setting-icon-note.component.css'
})
export class SettingIconNoteComponent implements OnInit{
  iconNotes:IconNote[] = [];
  type:string = "COST";
  readonly dialog = inject(MatDialog);
  constructor(private iconNoteService:IconNoteService, private authService:AuthService) {}
  ngOnInit(): void {
    this.iconNoteService.getAllIconNoteByUsername(this.authService.getUsername()).subscribe(iconNotes => {
      console.log(iconNotes);
      this.iconNotes = iconNotes;
    })
  }

  setType(type:string) {
    this.type = type;
  }

  openDialog(id:number) {
    const dialogRef = this.dialog.open(DeleteComponent);
    dialogRef.afterClosed().subscribe(result => {
      if(result === 'confirm') {
        this.iconNoteService.deleteIconNote(id).subscribe();
        for(let i = 0; i < this.iconNotes.length; i++) {
          if(this.iconNotes.at(i)?.id == id) {
            this.iconNotes.splice(i, 1);
            break;
          }
        }
      }
    })
  }

}
