import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, provideHttpClient, withFetch, withInterceptors, withInterceptorsFromDi } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideNativeDateAdapter } from '@angular/material/core';
import { AuthInterceptor } from './interceptor/auth.interceptor';
import { ErrorInterceptor } from './interceptor/error.interceptor';



@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [
    provideHttpClient(withFetch(), withInterceptorsFromDi())
    ,provideAnimationsAsync()
    ,provideNativeDateAdapter()
    ,{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
    ,{provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}]
    ,
  bootstrap: [AppComponent]
})
export class AppModule { }
