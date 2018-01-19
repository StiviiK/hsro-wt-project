import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { ForumCategory } from '../../models/forum/ForumCategory';
import { ForumCategoryApiResponse } from '../../models/interfaces/api/ApiResponse';
import { of } from 'rxjs/observable/of';
import { ApiService } from '../api/api.service';

@Injectable()
export class ForumCategoryService {
  categories = [
    new ForumCategory(1, 'Web technology', 'lightblue', []),
    new ForumCategory(2, 'Java', 'lightgreen', []),
    new ForumCategory(3, 'Unity', 'lightpink', []),
    new ForumCategory(4, 'Hardware', '#DDBDF1', []),
    new ForumCategory(5, 'Food', 'red', []),
    new ForumCategory(6, 'Crafting', 'orange', []),
    new ForumCategory(7, 'Sport', 'yellow', []),
    new ForumCategory(8, 'RL', 'grey', [])
  ];

  public categoriesX: ForumCategory[];

  constructor(private _api: ApiService) {}

  getForumCategories(): Observable<ForumCategory[]> {
    return of(this.categories);
  }

  get(id: number): Observable<ForumCategory> {

    if (ForumCategory.getById(id)) {
      return of(ForumCategory.getById(id));
    } else {
      return this._api.get<ForumCategoryApiResponse>('/category/' + id)
        .map(
          (response: ForumCategoryApiResponse): ForumCategory => {
            if (response && response.status === true) {
              return ForumCategory.get(response.data);
            }
          }
        );
      }
    }
}
