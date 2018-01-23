import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Thread } from '../../../models/forum/Thread';
import { ThreadService } from '../../../services/forum/thread';
import { ThreadAnswer } from '../../../models/forum/ThreadAnswer';
import { User } from '../../../models/user/User';
import { ForumCategory } from '../../../models/forum/ForumCategory';
import { ForumCategoryService } from '../../../services/forum/forum-category';

@Component({
  selector: 'app-forum-thread',
  templateUrl: './thread.component.html',
  styleUrls: ['./thread.component.scss']
})
export class ForumThreadComponent implements OnInit {
  thread: Thread;

  constructor(private _route: ActivatedRoute,
              private _router: Router,
              private _threadService: ThreadService,
              private _categoryService: ForumCategoryService) { }

  ngOnInit() {
    this._route.params.subscribe(params => {
      this.updateHistory(params['id'] as string);
      this._threadService.increaseViews(params['id']).subscribe(
        (status: boolean) => {
          if (!status) {
            console.error("Failed to increase view");
          }
        }
      );
      this._threadService.get(params['id']).subscribe(
        (thread: Thread) => {
          if (thread === undefined) {
            this._router.navigate(['error', '404']);
          } else {
            this._categoryService.get(thread._category).subscribe((category: ForumCategory) => {
              category.addThread(thread);
              thread.setCategory(category);
              this.thread = thread;
            });
          }
        }
      );
    });
  }

  private updateHistory(threadId: string): void {
    const lastVisited: string[] = JSON.parse(localStorage.getItem('lastVisited') || '[]');
    if (!lastVisited.find(id => id === threadId)) {
      lastVisited.unshift(threadId);
    }
    lastVisited.splice(4, 1);
    localStorage.setItem('lastVisited', JSON.stringify(lastVisited));
  }
}
