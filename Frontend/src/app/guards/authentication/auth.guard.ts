import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthenticatedUser } from '../../models/user/User';
import { ApiService } from '../../services/api/api.service';
import { ApiResponse } from '../../models/interfaces/api/ApiResponse';
import { of } from 'rxjs/observable/of';
import { AuthenticationService } from '../../services/authentication/authentication';

@Injectable()
export class AuthGuard implements CanActivate {
    constructor(private _router: Router,
                private _api: ApiService,
                private _authService: AuthenticationService) { }

    canActivate() {
      if (AuthenticatedUser.load() != null) {
        return this._api.get<ApiResponse>('Verify')
          .map(
            (response: ApiResponse): boolean => {
              if (response && response.status === true) {
                return true;
              }

              this._authService.logout();
              this._router.navigate(['/login']);
              return false;
            }
          )
          .catch(() => {
            this._authService.logout();
            this._router.navigate(['/login']);
            return of(false);
          });
      } else {
        this._authService.logout();
        this._router.navigate(['/login']);
        return of(false);
      }
    }
}
