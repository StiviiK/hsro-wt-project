import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthenticatedUser } from '../../models/user/User';
import { ApiService } from '../../services/api/api.service';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private _router: Router,
                private _api: ApiService) { }

    canActivate() {
      if (AuthenticatedUser.load() != null) {
        return true;
      }
      this._router.navigate(['/login']);
      return false;
    }
}
