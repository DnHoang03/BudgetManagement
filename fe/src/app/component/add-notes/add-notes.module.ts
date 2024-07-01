import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AddNotesRoutingModule } from './add-notes-routing.module';
import { AddNotesComponent } from './add-notes.component';
import { CostComponent } from './cost/cost.component';
import { IncomeComponent } from './income/income.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormField, MatInputModule } from '@angular/material/input';
import { MatSelect, MatSelectModule } from '@angular/material/select';
import { MatNativeDateModule, provideNativeDateAdapter } from '@angular/material/core';
import { InputComponent } from './input/input.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonToggle, MatButtonToggleGroup } from '@angular/material/button-toggle';
import { NumberFormatPipe } from '../../share/pipes/number-format.pipe';


@NgModule({
  declarations: [
    AddNotesComponent,
    CostComponent,
    IncomeComponent,
    TransactionsComponent,
    InputComponent,
    NumberFormatPipe
  ],
  imports: [
    CommonModule,
    AddNotesRoutingModule,
    ReactiveFormsModule,
    MatSidenavModule,
    MatDatepickerModule,
    MatInputModule,
    MatSelectModule,
    MatNativeDateModule,
    MatGridListModule,
    MatIconModule,
    MatButtonToggleGroup,
    MatFormField,
    MatButtonToggle
  ]
})
export class AddNotesModule { }
