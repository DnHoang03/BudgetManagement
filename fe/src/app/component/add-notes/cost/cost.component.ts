import { Component, OnInit } from '@angular/core';
import { Icon } from '../../../model/icon';
import { CostService } from '../../../service/cost.service';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { IconService } from '../../../service/icon.service';

@Component({
  selector: 'app-cost',
  templateUrl: './cost.component.html',
  styleUrl: './cost.component.css'
})
export class CostComponent implements OnInit{
  icons:Icon[] = [];
  iconForm:FormGroup = new FormGroup({
    amount: new FormControl(''),
    createdAt: new FormControl(''),
    note: new FormControl(''),
    imageUrl: new FormControl(''),
    iconId: new FormControl(''),
    userId: new FormControl('')
  });

  isAdding:boolean = true;
  iconAdding!:Icon;
  constructor(private costService:CostService, private formBuilder:FormBuilder, private iconService:IconService){
  }

  // getIcons() {
  //   return this.iconService.getAllCostIcon().subscribe(icons => {
  //     this.icons = icons;
  //   });
  // }


  setAdd(icon:Icon) {
    this.isAdding = true;
    this.iconAdding = icon;
  }

  ngOnInit(): void {
    // this.getIcons();
  }
}
