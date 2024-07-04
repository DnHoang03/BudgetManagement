import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatFormField, MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { InputComponent } from './component/input/input.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { NumberFormatPipe } from './pipes/number-format.pipe';


@NgModule({
  declarations: [
    InputComponent,
    NumberFormatPipe
  ],
  imports: [
    CommonModule,
    MatFormField,
    MatFormFieldModule,
    MatInputModule,
    MatGridListModule,
    MatDatepickerModule
  ],
  exports:[
    InputComponent
  ]
})
export class ShareModule { }
