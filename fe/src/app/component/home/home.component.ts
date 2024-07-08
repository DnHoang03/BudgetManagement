import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  selectedDate:Date = new Date();
  chosenMonthHandler(normalizedMonth: Date, datepicker: any) {
    const ctrlValue = this.selectedDate || new Date();
    ctrlValue.setMonth(normalizedMonth.getMonth());
    ctrlValue.setFullYear(normalizedMonth.getFullYear())
    this.selectedDate = ctrlValue;
    console.log(ctrlValue);
    console.log(this.selectedDate)
    datepicker.close();
  }
}
