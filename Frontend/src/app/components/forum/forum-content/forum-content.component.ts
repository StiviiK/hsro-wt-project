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
              private _route: ActivatedRoute,
              private _router: Router) {
  }

  ngOnInit() {
    this._route.params.subscribe(params => {
      this._forumCategoryService.get(params['categoryId']).subscribe(
          (category: ForumCategory) => {
            if (category === undefined) {
              this._router.navigate(['error', '404']);
            } else {
              category._threads.forEach(number => {
                this._threadService.get(number, category.id).subscribe(
                  (thread: Thread) => {
                    category.addThread(thread);
                  }
                );
              });
              this.category = category;
            }
          }
      );
    });
  }
}

