import { HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, handler: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');
    if (token === null) return handler.handle(req);
    const authRequest = req.clone(
      {
        headers: req.headers.set('Authorization', 'Bearer ' + token)
      }
    );
    return handler.handle(authRequest);
  }
}
