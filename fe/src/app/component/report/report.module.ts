import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReportRoutingModule } from './report-routing.module';
import { ReportComponent } from './report.component';
import { AccountComponent } from './account/account.component';
import { CreateAccountComponent } from './account/create-account/create-account.component';
import { MatFormField, MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';


@NgModule({
  declarations: [
    ReportComponent,
    AccountComponent,
    CreateAccountComponent
  ],
  imports: [
    CommonModule,
    ReportRoutingModule,
    MatFormFieldModule,
    MatSelectModule,
    MatFormField,
    MatInputModule
  ]
})
export class ReportModule { }
