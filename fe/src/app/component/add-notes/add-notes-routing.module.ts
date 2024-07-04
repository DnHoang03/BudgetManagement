import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddNotesComponent } from './add-notes.component';
import { CostComponent } from './cost/cost.component';
import { IncomeComponent } from './income/income.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { AddIconComponent } from './setting-icon-note/add-icon/add-icon.component';

const routes: Routes = [{ path: '', component: AddNotesComponent },
  {path:'cost', component: CostComponent},
  {path:'income', component: IncomeComponent},
  {path:'transactions', component: TransactionsComponent},
  {path:'setting/add-icon', component: AddIconComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AddNotesRoutingModule { }
