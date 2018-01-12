import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { ForumCategory } from '../../models/forum/ForumCategory';

import { of } from 'rxjs/observable/of';
import { Thread } from '../../models/forum/Thread';
import { User } from '../../models/user/User';
import { ApiService } from '../api/api.service';

const userA = new User(1, 'StiviK', 'fuck', 'you');
const userB = new User(2, 'Verocode', 'fuck', 'you');
const userC = new User(3, 'FatLoki', 'fuck', 'you');
const lorem_ipsum = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt' +
                    'ut labore et dolore magna aliqua. Hendrerit gravida rutrum quisque non tellus orci ac auctor ' +
                    'augue. Donec ac odio tempor orci. Lacus sed viverra tellus in hac habitasse platea dictumst ' +
                    'vestibulum. Adipiscing enim eu turpis egestas pretium aenean pharetra magna ac.';

const threads = [
  new Thread(1, 0, userA, 'Play two input with each output on two different channels simultaneously using sounddevice',
              lorem_ipsum, new Date(Date.now())),
  new Thread(2, 0, userB, 'Angular 4 is not working', lorem_ipsum, new Date('1/1/16')),
  new Thread(3, 0, userC, 'PC doesnt start, help please', lorem_ipsum, new Date('1/17/16')),
  new Thread(4, 0, userA, 'Work', lorem_ipsum, new Date('1/28/16')),
  new Thread(5, 0, userB, 'How do i do this computing thing', lorem_ipsum, new Date('2/20/16')),
  new Thread(6, 0, userC, 'Website Remodel', lorem_ipsum, new Date('2/20/16')),
];

@Injectable()
export class ForumCategoryService {
  categories = [
    new ForumCategory(1, 'Web technology', 'lightblue'),
    new ForumCategory(2, 'Java', 'lightgreen'),
    new ForumCategory(3, 'Unity', 'lightpink'),
    new ForumCategory(4, 'Hardware', '#DDBDF1'),
    new ForumCategory(5, 'Food', 'red'),
    new ForumCategory(6, 'Crafting', 'orange'),
    new ForumCategory(7, 'Sport', 'yellow'),
    new ForumCategory(8, 'RL', 'grey')
  ];

  public categoriesX: ForumCategory[];

  constructor(private _api: ApiService) {
    this.categories[0].addThread(threads[0]);
    this.categories[0].addThread(threads[1]);
    this.categories[1].addThread(threads[2]);
    this.categories[1].addThread(threads[3]);
    this.categories[2].addThread(threads[4]);
    this.categories[4].addThread(threads[5]);
  }

  getForumCategories(): Observable<ForumCategory[]> {
    return of(this.categories);
  }

  getForumCategory(id: number): Observable<ForumCategory> {
    id = id - 1;
    return of(this.categories[id]);
  }

}
