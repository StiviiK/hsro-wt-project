import { Component, OnInit } from '@angular/core';
import { ForumCategoryService } from '../../services/forum/forum-category';
import { ForumCategory } from '../../models/forum/ForumCategory';

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.scss'],

})
export class ForumComponent implements OnInit {

  public categories: ForumCategory[];

  constructor(private _forumCategoryService: ForumCategoryService) { }

  ngOnInit() {
    this._forumCategoryService.getForumCategories().subscribe(
      (categories: ForumCategory[]) => {
        this.categories = categories;
      }
    )
  }
}