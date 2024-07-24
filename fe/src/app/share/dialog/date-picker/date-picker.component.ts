import { Component, Inject, OnInit } from '@angular/core';
import {  MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-date-picker',
  templateUrl: './date-picker.component.html',
  styleUrl: './date-picker.component.css'
})
export class DatePickerComponent implements OnInit {
  months:number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
  selectedYear:number = new Date().getFullYear();
  selectedMonth:number = new Date().getMonth()+1;
  constructor(
    private dialogRef:MatDialogRef<DatePickerComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
   
  }
  ngOnInit(): void {
    this.selectedMonth = this.data.getMonth()+1;
    this.selectedYear = this.data.getFullYear();
  }
  
  selectMonth(month:number) {
    this.selectedMonth = month;
  }

  upYear() {
    this.selectedYear += 1;
  }

  downYear() {
    this.selectedYear -= 1;
  }

  close() {
    this.dialogRef.close()
  }

  selectDate() {
    let v = new Date()
    v.setMonth(this.selectedMonth-1)
    v.setFullYear(this.selectedYear)
    this.dialogRef.close(v)
  }

}
