import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthenticatedUser } from '../../models/user/User';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private _router: Router) { }

    canActivate() {
      if (AuthenticatedUser.load() == null) {
        this._router.navigate(['/login']);
        return false;
      }
      return true;
    }
}
