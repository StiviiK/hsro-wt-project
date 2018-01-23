import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { ForumCategory } from '../../models/forum/ForumCategory';
import { ForumCategoryApiResponse, ForumCategorysApiResponse } from '../../models/interfaces/api/ApiResponse';
import { of } from 'rxjs/observable/of';
import { ApiService } from '../api/api.service';
import { ForumCategoryJson } from '../../models/interfaces/api/JsonResponse';

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
    return this._api.get<ForumCategorysApiResponse>('Forum')
      .map(
        (response: ForumCategorysApiResponse): ForumCategory[] => {
          if (response && response.status === true) {
            const categories: ForumCategory[] = [];
            response.data.forEach(
              (json: ForumCategoryJson) => {
                categories.push(ForumCategory.get(json));
              }
            )

            return categories;
          }
        }
      )
  }

  get(id: number): Observable<ForumCategory> {
  if (ForumCategory.getById(id)) {
    return of(ForumCategory.getById(id));
  } else {
    return this._api.get<ForumCategoryApiResponse>('Forum/' + id)
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
