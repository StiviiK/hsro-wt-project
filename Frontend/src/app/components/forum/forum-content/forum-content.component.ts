import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ForumCategory } from '../../../models/forum/ForumCategory';
import { ForumCategoryService } from '../../../services/forum/forum-category';

@Component({
  selector: 'app-forum-content',
  templateUrl: './forum-content.component.html',
  styleUrls: ['./forum-content.component.css']
})
export class ForumContentComponent implements OnInit {
  public category: ForumCategory;

  constructor(private forumCategoryService: ForumCategoryService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    let id: number;
    this.route.params.subscribe(params => {
      id = params['categoryId'];
    });
    this.forumCategoryService.getForumCategory(id).subscribe(
      category => {
        this.category = category;
      }
    );
  }
}

