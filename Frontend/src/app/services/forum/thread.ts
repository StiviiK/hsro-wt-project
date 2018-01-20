import { Injectable } from '@angular/core';
import { Thread } from '../../models/forum/Thread';
import { ThreadAnswer } from '../../models/forum/ThreadAnswer';
import { of } from 'rxjs/observable/of';
import { Observable } from 'rxjs/Observable';
import { ApiService } from '../api/api.service';
import { ThreadApiResponse } from '../../models/interfaces/api/ApiResponse';
import { ThreadAnswerJson } from '../../models/interfaces/api/JsonResponse';

@Injectable()
export class ThreadService {

  private static route = '/api/threads';
  private threads = []; // debug only, fetch later from api

  constructor(private _api: ApiService) {}

  get(id: number): Observable<Thread> {
    if (Thread.getById(id)) {
      return of(Thread.getById(id));
    } else {
      return this._api.get<ThreadApiResponse>('thread/' + id)
        .map(
            (response: ThreadApiResponse): Thread => {
              if (response && response.status === true) {
                const data = response.data;
                const thread: Thread = Thread.get(response.data);

                const threadAnswer: ThreadAnswer[] = [];
                data.answers.forEach((json: ThreadAnswerJson) => {
                  threadAnswer.push(ThreadAnswer.get(json, thread));
                });

                return thread;
              }
            }   
        )
      }
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
        threads.push(self.threads[item - 1]);
      }
    );

    return of(threads);
  }
}
