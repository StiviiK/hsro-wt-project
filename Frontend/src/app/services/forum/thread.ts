import { Injectable } from '@angular/core';
import { Thread } from '../../models/forum/Thread';
import { User, AuthenticatedUser } from '../../models/user/User';
import { ThreadAnswer } from '../../models/forum/ThreadAnswer';

import { of } from 'rxjs/observable/of';
import { Observable } from 'rxjs/Observable';
import { ApiService } from '../api/api.service';
import { ApiRequest } from '../../models/interfaces/api/ApiRequest';
// import { ApiResponse, ThreadApiResponse, UserApiResponse } from '../../models/interfaces/api/ApiResponse';
import { mergeMap } from 'rxjs/operators/mergeMap';

const userA = new User(1, 'Stefan Kürzeder', 'test@gmail.com', '');
const userB = new User(2,  'Erik van Slingerland', 'test@gmail.com', '');
const userC = new User(3, 'Moritz Dietl', 'test@gmail.com', '');
const lorem_ipsum = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Hendrerit gravida rutrum quisque non tellus orci ac auctor augue. Donec ac odio tempor orci. Lacus sed viverra tellus in hac habitasse platea dictumst vestibulum. Adipiscing enim eu turpis egestas pretium aenean pharetra magna ac.';

@Injectable()
export class ThreadService {

  private static route = '/api/threads';
  private threads = [
    new Thread(1, 0, userA, 'Play two input with each output on two different channels simultaneously using sounddevice', lorem_ipsum, new Date(Date.now())),
    new Thread(2, 0, userC, 'Angular 4 is not working', lorem_ipsum, new Date('1/1/16')),
    new Thread(3, 0, userB, 'PC doesnt start, help please', lorem_ipsum, new Date('1/17/16')),
    new Thread(4, 0, userB, 'Work', lorem_ipsum, new Date('1/28/16')),
    new Thread(5, 0, userA, 'How do i do this computing thing', lorem_ipsum, new Date('2/20/16')),
    new Thread(6, 0, userC, 'Website Remodel', lorem_ipsum, new Date('2/20/16')),
  ]; // debug only, fetch later from api

  // IMPORTANT: CACHING?

  constructor(private _api: ApiService) {
    this.threads[1].addAnswer(new ThreadAnswer(1, this.threads[1], userA, 'Angular? can u eat this?'));

    this.threads[2].addAnswer(new ThreadAnswer(2, this.threads[2], userA, 'You fucked up!'));
    this.threads[2].addAnswer(new ThreadAnswer(3, this.threads[2], userB, 'Haha, nice!'));
    this.threads[2].addAnswer(new ThreadAnswer(4, this.threads[2], userC, 'I just wanted to say hi!'));

    this.threads[5].addAnswer(new ThreadAnswer(5, this.threads[5], userB, 'Why do u ask me?'));
  }

  get(id: number, fetchAnswers: boolean): Observable<Thread> {
    // Step 1: fetches thread from api (without answers, boolean to disable answer fetching)
    // Step 2 (optional): ask ThreadAnswer service to get answers for this thread and attach/add them

    // Example code
    // return this._api.get<ThreadApiResponse>('thread/' + id, {})
    //   .pipe(
    //     mergeMap(
    //       (response: ThreadApiResponse): Observable<Thread> => {
    //         const data = response.data;
    //         let observable = this.getUser(data.creator)
    //           .map(
    //             (user: User): Thread => {
    //               return new Thread(data.id, data.views, user, data.topic, data.question, new Date(data.lastUpdate));
    //             }
    //           );

    //         if (fetchAnswers === true) {
    //           observable = observable.pipe(
    //             mergeMap(
    //               (thread: Thread): Observable<Thread> => {
    //                 return this.getAnswers(id)
    //                   .map(
    //                     (threadAnswer: ThreadAnswer): Thread => {
    //                       thread.addAnswer(threadAnswer);
    //                       return thread;
    //                     }
    //                   );
    //               }
    //             )
    //           );
    //         }

    //         return observable;
    //       }
    //     )
    //   );
    return of(this.threads[id - 1]);
  }

  // Example code
  // getUser(id: number): Observable<User> {
  //   return of(userA);
  // }

  // getAnswers(id: number): Observable<ThreadAnswer> {
  //   return of(new ThreadAnswer(1, this.threads[1], userA, 'Angular? can u eat this?'));
  // }

  getHottest(): Observable<Thread[]> {
    /**
     * Idea 1:
     *  - Step 1: fetch thread ids from api
     *  - Step 2: fetch Threads via the Thread Service get-Method (without answers, loading time speedup)
     *
     * Idea 2:
     *  - Step 1: fetch direct the threads in this method (redudant code? because does same as Thread Service get-Method)
     */

    return of([
      this.threads[0],
      this.threads[1],
      this.threads[2],
      this.threads[3],
      this.threads[4],
      this.threads[5],
    ]);
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
