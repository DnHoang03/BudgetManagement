import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';

import { ReportRoutingModule } from './report-routing.module';
import { ReportComponent } from './report.component';
import { AccountComponent } from './account/account.component';
import { CreateAccountComponent } from './account/create-account/create-account.component';
import { MatFormField, MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { ShareModule } from '../../share/share.module';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatGridListModule } from '@angular/material/grid-list';
import { FormsModule } from '@angular/forms';
import { ManageAccountComponent } from './account/manage-account/manage-account.component';
import { MatListModule } from '@angular/material/list';
import { AccountDetailComponent } from './account/account-detail/account-detail.component';
import { AnalyseComponent } from './analyse/analyse.component';


@NgModule({
  declarations: [
    ReportComponent,
    AccountComponent,
    CreateAccountComponent,
    ManageAccountComponent,
    AccountDetailComponent,
    AnalyseComponent,
  ],
  imports: [
    CommonModule,
    ReportRoutingModule,
    MatFormFieldModule,
    MatSelectModule,
    MatFormField,
    MatInputModule,
    ShareModule,
    MatDatepickerModule,
    MatGridListModule,
    FormsModule,
    MatListModule,
    MatDatepickerModule
  ]
})
export class ReportModule { }
