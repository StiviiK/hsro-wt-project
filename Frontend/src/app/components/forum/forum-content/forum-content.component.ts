import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ForumCategory } from '../../../models/forum/ForumCategory';
import { ForumCategoryService } from '../../../services/forum/forum-category';
import { ThreadService } from '../../../services/forum/thread';
import { Thread } from '../../../models/forum/Thread';

@Component({
  selector: 'app-forum-content',
  templateUrl: './forum-content.component.html',
  styleUrls: ['./forum-content.component.css']
})
export class ForumContentComponent implements OnInit {
  public category: ForumCategory;

  constructor(private _forumCategoryService: ForumCategoryService,
              private _threadService: ThreadService,
              private _route: ActivatedRoute) {
  }

  ngOnInit() {
    let id: number;
    this._route.params.subscribe(params => {
      this._forumCategoryService.get(params['categoryId']).subscribe(
          (category: ForumCategory) => {
            console.log(category);
            category._threads.forEach(number => {
              this._threadService.get(number).subscribe(
                (thread: Thread) => {
                  category.addThread(thread);
                }
              )
            })
            this.category = category;
          }
      );
    });
  }
}

