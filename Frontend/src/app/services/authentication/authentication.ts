import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { HttpClient, HttpResponse } from '@angular/common/http';

import { AuthService, GoogleLoginProvider, SocialUser } from 'ng4-social-login';
import { mergeMap } from 'rxjs/operators/mergeMap';
import { fromPromise } from 'rxjs/observable/fromPromise';
import { AuthenticatedUser } from '../../models/user/User';

import { JWTApiRequest, ApiRequest } from '../../models/interfaces/api/ApiRequest';
import { JWTApiResponse, ApiResponse } from '../../models/interfaces/api/ApiResponse';
import { ApiService } from '../api/api.service';

@Injectable()
export class AuthenticationService {

  public token: string;

  constructor(private _api: ApiService, private _oauthService: AuthService) {
    const currentUser = AuthenticatedUser.load();
    this.token = currentUser && currentUser.token;
  }

  public login(): Observable<boolean> {
    return fromPromise(this._oauthService.signIn(GoogleLoginProvider.PROVIDER_ID))
      .pipe(
        mergeMap(
          (user: SocialUser): Observable<boolean> => {
            if (user && user.token) {
              const payload: JWTApiRequest = {
                googleToken: user.token,
                user: {
                  email: user.email,
                  name: user.name,
                  avatar: user.photoUrl
                }
              };

              return this._api.post<JWTApiResponse>('jwt/login', payload)
                .map(
                  (response: JWTApiResponse): boolean => {
                    if (response && response.status === true) {
                      this.token = response.data.token;
                      AuthenticatedUser.save(new AuthenticatedUser(response.data.user.id, user, response.data.token));

                      return true;
                    }
                    return false;
                  }
                );
            } else {
              return of(false);
            }
          }
        )
      );
  }

  public logout(): boolean {
    if (AuthenticatedUser.load()) {
      // const payload: ApiRequest = {};
      // this._api.post<ApiResponse>('logout', payload)
      //   .subscribe(console.log, console.error);

      this.token = null;
      AuthenticatedUser.clear();

      return AuthenticatedUser.load() == null;
    }
    return false;
  }

}

/** /login
 * {
 *  token: 3gu8dhfgf239r348t,
 *  username: 'Erik',
 *  id: 5
 * }
 */
