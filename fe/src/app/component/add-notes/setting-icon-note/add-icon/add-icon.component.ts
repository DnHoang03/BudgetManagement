import { Component, OnInit } from '@angular/core';
import { IconService } from '../../../../service/icon.service';
import { Icon } from '../../../../model/icon';
import { IconNote } from '../../../../model/iconNote';
import { IconCategoryService } from '../../../../service/icon-category.service';
import { IconCategory } from '../../../../model/iconCategory';
import { IconNoteService } from '../../../../service/icon-note.service';

@Component({
  selector: 'app-add-icon',
  templateUrl: './add-icon.component.html',
  styleUrl: './add-icon.component.css'
})
export class AddIconComponent implements OnInit{

  icons!:Icon[];
  iconCategories!:IconCategory[];
  categoryId:number = 1;
  iconNote:IconNote = {
    name:'',
    iconId:1,
    iconNoteType:'COST'
  };
  constructor(private iconService:IconService
    , private iconCategoryService:IconCategoryService
    , private iconNoteService:IconNoteService){}
  ngOnInit(): void {
    this.iconService.getAllNoteIcon().subscribe(icons => {
      this.icons = icons;
    })
    this.iconCategoryService.getAllIconCategory().subscribe(categories => {
      this.iconCategories = categories;
    })
  }

  setSelectedIcon(icon: Icon) {
    this.iconNote.iconId = icon.id;
  }


  createIconNote() {
    console.log(this.iconNote);
    this.iconNoteService.createIconNote(this.iconNote).subscribe();
  }
}
