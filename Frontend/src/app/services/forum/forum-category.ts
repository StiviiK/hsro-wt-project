import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { ForumCategory } from '../../models/forum/ForumCategory';
import { ForumCategoryApiResponse, ForumCategorysApiResponse } from '../../models/interfaces/api/ApiResponse';
import { of } from 'rxjs/observable/of';
import { ApiService } from '../api/api.service';
import { ForumCategoryJson } from '../../models/interfaces/api/JsonResponse';

@Injectable()
export class ForumCategoryService {

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
