import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from '../service/auth.service';
import { ErrorPopupService } from '../service/error-popup.service';
import { Router } from '@angular/router';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private authService:AuthService, private errorPopupService:ErrorPopupService, private router:Router){}
  intercept(req: HttpRequest<any>, handler: HttpHandler): Observable<HttpEvent<any>> {
    return handler.handle(req).pipe(
      catchError(
        (error:HttpErrorResponse) => {
          if(error.error.status === 500 && error.error.message === 'Token expired') {
            this.authService.refreshToken();
          } else if(error.error.status === 404 && error.error.message === 'Username invalid') {
            this.errorPopupService.showError('Tên đăng nhập không hợp lệ!')
            console.log(1)
          } else if(error.error.status === 401 && error.error.message === 'Bad credentials') {
            this.errorPopupService.showError('Sai mật khẩu!')
          } else if(error.error.status === 403 && error.error.message === 'Access Denied') {
            this.errorPopupService.showError('Bạn chưa đăng nhập')
            this.router.navigate(['/login']);
          }
          return throwError(()=>error)
        }
      )
    );
  }
}
