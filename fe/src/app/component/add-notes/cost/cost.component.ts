import { Component, OnInit } from '@angular/core';
import { Icon } from '../../../model/icon';
import { CostService } from '../../../service/cost.service';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { NotesService } from '../../../service/notes.service';
import { Note } from '../../../model/note';
import { IconService } from '../../../service/icon.service';

@Component({
  selector: 'app-cost',
  templateUrl: './cost.component.html',
  styleUrl: './cost.component.css'
})
export class CostComponent implements OnInit{
  icons:Icon[] = [];
  note!:Note;
  iconForm:FormGroup = new FormGroup({
    amount: new FormControl(''),
    createdAt: new FormControl(''),
    note: new FormControl(''),
    imageUrl: new FormControl(''),
    iconId: new FormControl(''),
    userId: new FormControl('')
  });

  isAdding:boolean = false;
  iconAdding!:Icon;
  constructor(private costService:CostService, private formBuilder:FormBuilder, private notesService:NotesService, private iconService:IconService){
  }

  // getIcons() {
  //   return this.iconService.getAllCostIcon().subscribe(icons => {
  //     this.icons = icons;
  //   });
  // }

  createNote() {
    return this.notesService.createNote(Object.assign(this.note, this.iconForm)).subscribe(icon => {
      return icon;
    });
  }

  setAdd(icon:Icon) {
    this.isAdding = true;
    this.iconAdding = icon;
  }

  ngOnInit(): void {
    // this.getIcons();
  }
}
