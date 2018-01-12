import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import { ApiService } from '../api/api.service';

import { User } from '../../models/user/User';
import { UserApiResponse } from '../../models/interfaces/api/ApiResponse';

@Injectable()
export class UserService {

  private users: Map<number, User>;

  constructor(private _api: ApiService) {
    this.users = new Map();
  }

  get(id: number): Observable<User> {
    if (this.users[id]) {
      return of(this.users[id]);
    }

    return this._api.get<UserApiResponse>('user/' + id)
      .map(
        (response: UserApiResponse): User => {
          if (response && response.status === true) {
            const data = response.data;
            const user = new User(data.id, data.name, data.email, data.avatar_url);
            this.users.set(id, user);

            return user;
          } else {
            console.error(response.error);
          }
          return null;
        }
      );
  }
}
