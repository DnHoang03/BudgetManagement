import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReportComponent } from './report.component';
import { CreateAccountComponent } from './account/create-account/create-account.component';
import { ManageAccountComponent } from './account/manage-account/manage-account.component';
import { AccountDetailComponent } from './account/account-detail/account-detail.component';
import { AccountComponent } from './account/account.component';

const routes: Routes = [
  { path: '', component: ReportComponent },
  { path: 'account/create', component:CreateAccountComponent},
  { path: 'account/manage', component:ManageAccountComponent},
  { path: 'account/manage/:id', component:CreateAccountComponent},
  { path: 'account/detail/:id', component:AccountDetailComponent},
  { path: 'account', component:AccountComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReportRoutingModule { }
