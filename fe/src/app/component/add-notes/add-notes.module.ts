import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AddNotesRoutingModule } from './add-notes-routing.module';
import { AddNotesComponent } from './add-notes.component';
import { CostComponent } from './cost/cost.component';
import { IncomeComponent } from './income/income.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormField, MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatNativeDateModule} from '@angular/material/core';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { ShareModule } from '../../share/share.module';
import { SettingIconNoteComponent } from './setting-icon-note/setting-icon-note.component';
import { AddIconComponent } from './setting-icon-note/add-icon/add-icon.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';


@NgModule({
  declarations: [
    AddNotesComponent,
    CostComponent,
    IncomeComponent,
    TransactionsComponent,
    SettingIconNoteComponent,
    AddIconComponent
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
    MatFormFieldModule,
    MatFormField,
    FormsModule,
    ShareModule,
    MatButtonModule
  ]
})
export class AddNotesModule { }
