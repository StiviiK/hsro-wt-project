import { Component, OnInit } from '@angular/core';
import { ForumCategoryService } from '../../services/forum/forum-category';
import { ForumCategory } from '../../models/forum/ForumCategory';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.scss'],

})
export class ForumComponent implements OnInit {

  public categories: ForumCategory[];

  constructor(private _router: Router,
              private _forumCategoryService: ForumCategoryService) { }

  ngOnInit() {
    this._forumCategoryService.getForumCategories().subscribe(
      (categories: ForumCategory[]) => {
        if (categories === undefined) {
          this._router.navigate(['error', '404']);
        } else {
          this.categories = categories;
        }
      }
    );
  }
}
