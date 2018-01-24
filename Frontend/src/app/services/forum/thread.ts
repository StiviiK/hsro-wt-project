import { Injectable } from '@angular/core';
import { Thread } from '../../models/forum/Thread';
import { ThreadAnswer } from '../../models/forum/ThreadAnswer';
import { of } from 'rxjs/observable/of';
import { Observable } from 'rxjs/Observable';
import { ApiService } from '../api/api.service';
import { ThreadApiResponse, ThreadCreateApiResponse, ThreadAnswerApiResponse, ApiResponse } from '../../models/interfaces/api/ApiResponse';
import { ThreadAnswerJson } from '../../models/interfaces/api/JsonResponse';
import { ThreadApiRequest, ThreadAnswerApiRequest } from '../../models/interfaces/api/ApiRequest';

@Injectable()
export class ThreadService {

  private static route = '/api/threads';
  private threads = []; // debug only, fetch later from api

  constructor(private _api: ApiService) {}

  get(id: number): Observable<Thread> {
    return this._api.get<ThreadApiResponse>('Forum/0/Post/' + id)
      .map(
          (response: ThreadApiResponse): Thread => {
            if (response && response.status === true) {
              const data = response.data;
              const thread: Thread = Thread.get(response.data);

              data.answers.forEach((json: ThreadAnswerJson) => {
                thread.answers.push(ThreadAnswer.get(json, thread));
              });
              return thread;
            }
          }
      )
      .catch(() => of(undefined));
  }

  getHottest(): Observable<Thread[]> {
    /**
     * Idea 1:
     *  - Step 1: fetch thread ids from api
     *  - Step 2: fetch Threads via the Thread Service get-Method (without answers, loading time speedup)
     *
     * Idea 2:
     *  - Step 1: fetch direct the threads in this method (redudant code? because does same as Thread Service get-Method)
     */

    return of();
  }

  getLastVisited(): Observable<Thread[]> {
    const threads = [];
    const self = this;
    JSON.parse(localStorage.getItem('lastVisited') || '[]').forEach(
      (item: any) => {
        this.get(item)
          .toPromise()
          .then((thread) => {
              threads.push(thread);
          });
      }
    );

    return of(threads);
  }

  increaseViews(id: number): Observable<boolean> {
    return this._api.get<ApiResponse>('Forum/0/Post/' + id + '/View')
      .map(
        (response: ApiResponse ): boolean => {
          return response && response.status === true;
        }
      )
      .catch(() => of(false));
  }

  create(forumId: number, payload: ThreadApiRequest): Observable<number> {
    return this._api.post<ThreadCreateApiResponse>('Forum/' + forumId, payload)
      .map(
        (response: ThreadCreateApiResponse): number => {
          if (response && response.status === true) {
            return response.data.id;
          }
        }
      )
      .catch(() => of(undefined));
  }

  createAnswer(threadId: number, payload: ThreadAnswerApiRequest): Observable<boolean> {
    return this._api.post<ThreadAnswerApiResponse>('Forum/0/Post/' + threadId, payload)
      .map(
        (response: ThreadAnswerApiResponse): boolean => {
          return response && response.status;
        }
      )
      .catch(() => of(undefined));
  }
}
