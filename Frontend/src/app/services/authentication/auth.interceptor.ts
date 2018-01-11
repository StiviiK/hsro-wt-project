import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs/observable';
import { AuthenticatedUser } from '../../models/user/User';

@Injectable()
export class AuthInterceptor implements HttpInterceptor { // Injects the authorization token into the http requests

  public constructor() { }

  intercept (req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq;
    const user = AuthenticatedUser.load();
    if (user) {
      authReq = req.clone({
        headers: req.headers.set('Access-Control-Allow-Origin', '*')
                            .set('Authorization', 'Bearer ' + user.token)
      });
    } else {
      authReq = req.clone({
        headers: req.headers.set('Access-Control-Allow-Origin', '*')
      });
    }
    return next.handle(authReq);
  }

}
