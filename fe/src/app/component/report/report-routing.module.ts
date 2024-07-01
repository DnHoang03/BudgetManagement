import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReportComponent } from './report.component';
import { CreateAccountComponent } from './account/create-account/create-account.component';

const routes: Routes = [
  { path: '', component: ReportComponent },
  { path: 'account/create', component:CreateAccountComponent}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReportRoutingModule { }
