import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'report', loadChildren: () => import('./component/report/report.module').then(m => m.ReportModule) },
  { path: 'chart', loadChildren: () => import('./component/chart/chart.module').then(m => m.ChartModule) },
  { path: 'profile', loadChildren: () => import('./component/profile/profile.module').then(m => m.ProfileModule) },
  { path: 'add-notes', loadChildren: () => import('./component/add-notes/add-notes.module').then(m => m.AddNotesModule) },
  { path: 'home', loadChildren: () => import('./component/home/home.module').then(m => m.HomeModule) },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'share', loadChildren: () => import('./share/share.module').then(m => m.ShareModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
