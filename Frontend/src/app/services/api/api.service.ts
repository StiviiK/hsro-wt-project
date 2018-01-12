import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { ApiRequest } from '../../models/interfaces/api/ApiRequest';
import { ApiResponse } from '../../models/interfaces/api/ApiResponse';


@Injectable()
export class ApiService {

  private url = '//localhost:3000/api/';

  constructor(private _http: HttpClient) { }

  public get<T extends ApiResponse>(route: string): Observable<T> {
    return this._http.get<T>(this.url + route);
  }

  public post<T extends ApiResponse>(route: string, payload: ApiRequest): Observable<T> {
    return this._http.post<T>(this.url + route, payload);
  }

  public put<T extends ApiResponse>(route: string, payload: ApiRequest): Observable<T> {
    return this._http.put<T>(this.url + route, payload);
  }

  public delete<T extends ApiResponse>(route: string): Observable<T> {
    return this._http.delete<T>(this.url + route);
  }
}
