import { Injectable } from '@angular/core';
import { Thread } from '../../classes/forum/Thread';
import { User } from '../../classes/user/User';
import { ThreadAnswer } from '../../classes/forum/ThreadAnswer';

import { of } from 'rxjs/observable/of';
import { Observable } from 'rxjs/Observable';

const userA = new User(1, 'StiviK');
const userB = new User(2, 'Verocode');
const userC = new User(3, 'FatLoki');
const lorem_ipsum = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Hendrerit gravida rutrum quisque non tellus orci ac auctor augue. Donec ac odio tempor orci. Lacus sed viverra tellus in hac habitasse platea dictumst vestibulum. Adipiscing enim eu turpis egestas pretium aenean pharetra magna ac.';

@Injectable()
export class ThreadService {

  private static route = '/api/threads';
  private threads = [
    new Thread(1, 0, userA, 'Play two input with each output on two different channels simultaneously using sounddevice', lorem_ipsum, new Date(Date.now())),
    new Thread(2, 0, userA, 'Angular 4 is not working', lorem_ipsum, new Date('1/1/16')),
    new Thread(3, 0, userA, 'PC doesnt start, help please', lorem_ipsum, new Date('1/17/16')),
    new Thread(4, 0, userA, 'Work', lorem_ipsum, new Date('1/28/16')),
    new Thread(5, 0, userA, 'How do i do this computing thing', lorem_ipsum, new Date('2/20/16')),
    new Thread(6, 0, userA, 'Website Remodel', lorem_ipsum, new Date('2/20/16')),
  ]; // debug only, fetch later from api

  // IMPORTANT: CACHING?

  constructor() {
    this.threads[1].addAnswer(new ThreadAnswer(1, this.threads[1], userA, 'Angular? can u eat this?'));

    this.threads[2].addAnswer(new ThreadAnswer(2, this.threads[2], userA, 'You fucked up!'));
    this.threads[2].addAnswer(new ThreadAnswer(3, this.threads[2], userB, 'Haha, nice!'));
    this.threads[2].addAnswer(new ThreadAnswer(4, this.threads[2], userC, 'I just wanted to say hi!'));

    this.threads[5].addAnswer(new ThreadAnswer(5, this.threads[5], userB, 'Why do u ask me?'));
  }

  get(id: number, fetchAnswers: boolean): Observable<Thread> {
    // Step 1: fetches thread from api (without answers, boolean to disable answer fetching)
    // Step 2 (optional): ask ThreadAnswer service to get answers for this thread and attach/add them
    return of(this.threads[id - 1]);
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
