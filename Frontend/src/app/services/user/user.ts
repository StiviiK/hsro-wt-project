import { Injectable } from '@angular/core';
import { ApiService } from '../api/api.service';
import { Observable } from 'rxjs/Observable';
import { User } from '../../models/user/User';
import { UserApiResponse } from '../../models/interfaces/api/ApiResponse';

@Injectable()
export class UserService {

  constructor(private _api: ApiService) { }

  get(id: number): Observable<User> {
    return this._api.get<UserApiResponse>('Users/' + id)
      .map(
        (response: UserApiResponse): User => {
          if (response && response.status === true) {
            const user = User.get(response.data.user);
            user._answeredThreads = response.data.answeredThreads;
            user._topics = response.data.threads;
            return user;
          }
        }
      );
  }
}
