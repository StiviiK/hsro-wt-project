import { Component, OnInit } from '@angular/core';
import {ForumCategory} from '../../../models/forum/ForumCategory';
import {ActivatedRoute} from '@angular/router';
import {ForumCategoryService} from '../../../services/forum/forum-category';

@Component({
  selector: 'app-thread-create',
  templateUrl: './thread-create.component.html',
  styleUrls: ['./thread-create.component.css']
})
export class ThreadCreateComponent implements OnInit {
  public category: ForumCategory;

  constructor(private _route: ActivatedRoute, private _forumCategoryService: ForumCategoryService) { }

  ngOnInit() {
    this._route.params.subscribe(params => {
      this._forumCategoryService.get(params['categoryId']).subscribe(
        (category: ForumCategory) => {
          this.category = category;
        }
      );
    });
  }
}
