import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from '../service/auth.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private authService:AuthService){}
  intercept(req: HttpRequest<any>, handler: HttpHandler): Observable<HttpEvent<any>> {
    return handler.handle(req).pipe(
      catchError(
        (error:HttpErrorResponse) => {
          if(error.error.status === 500 && error.error.message === 'Token expired') {
            this.authService.refreshToken();
          }
          return throwError(()=>error)
        }
      )
    );
  }
}
