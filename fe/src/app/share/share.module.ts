import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatFormField, MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { InputComponent } from './component/input/input.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { NumberFormatPipe } from './pipes/number-format.pipe';
import { DeleteComponent } from './dialog/delete/delete.component';
import { MatDialogActions, MatDialogContent, MatDialogTitle } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { SelectAccountComponent } from './dialog/select-account/select-account.component';
import { MatListModule } from '@angular/material/list';
import { IconNoteInputComponent } from './dialog/icon-note-input/icon-note-input.component';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    InputComponent,
    NumberFormatPipe,
    DeleteComponent,
    SelectAccountComponent,
    IconNoteInputComponent
  ],
  imports: [
    CommonModule,
    MatFormField,
    MatFormFieldModule,
    MatInputModule,
    MatGridListModule,
    MatDatepickerModule,
    MatDialogContent,
    MatDialogActions,
    MatButtonModule,
    MatDialogTitle,
    MatListModule,
    MatIconModule,
    FormsModule
  ],
  exports:[
    InputComponent,
    DeleteComponent,
    SelectAccountComponent,
    IconNoteInputComponent
  ]
})
export class ShareModule { }
