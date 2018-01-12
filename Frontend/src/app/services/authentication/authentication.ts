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
import { race } from 'rxjs/operators/race';

interface APILoginResponse {
  token: string;
  username: string;
  id: number;
}

@Injectable()
export class AuthenticationService {

  public token: string;

  constructor(private _http: HttpClient, private _oauthService: AuthService) {
    const currentUser = AuthenticatedUser.load();
    this.token = currentUser && currentUser.token;
  }

  public login(): Observable<boolean> {
    return fromPromise(this._oauthService.signIn(GoogleLoginProvider.PROVIDER_ID))
      .pipe(
        mergeMap(
          (user: SocialUser) => {
            if (user && user.token) {
              return this._http.post<APILoginResponse>('//localhost:3000/jwt/get/',
                  JSON.stringify({
                    googleToken: user.token,
                    email: user.email, // unique + primary key
                    name: user.name,
                    avatar: user.photoUrl
                  })
                ).map(
                  (response: APILoginResponse) => {
                    if (response && response.token) {
                      this.token = response.token;
                      AuthenticatedUser.save(new AuthenticatedUser(response.id, user, response.token));

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
    this._http.post('//localhost:3000/api/logout', {})
      .subscribe(console.log, console.error);

    this.token = null;
    AuthenticatedUser.clear();

    return AuthenticatedUser.load() == null;
  }

}

/** /login
 * {
 *  token: 3gu8dhfgf239r348t,
 *  username: 'Erik',
 *  id: 5
 * }
 */
