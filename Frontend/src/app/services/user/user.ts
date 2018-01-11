import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { User } from '../../models/user/User';

@Injectable()
export class UserService {

  constructor(private _http: HttpClient) { }

  get(id: number): Observable<User> {
    return this._http.get<User>('//localhost:3000/api/user/1', {});
  }
}
