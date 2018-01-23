import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Thread } from '../../../models/forum/Thread';
import { ThreadService } from '../../../services/forum/thread';
import { ThreadAnswer } from '../../../models/forum/ThreadAnswer';
import { User, AuthenticatedUser } from '../../../models/user/User';
import { ForumCategory } from '../../../models/forum/ForumCategory';
import { ForumCategoryService } from '../../../services/forum/forum-category';
import { ThreadAnswerApiRequest } from '../../../models/interfaces/api/ApiRequest';

@Component({
  selector: 'app-forum-thread',
  templateUrl: './thread.component.html',
  styleUrls: ['./thread.component.scss']
})
export class ForumThreadComponent implements OnInit {
  public thread: Thread;
  public answer: string;

  constructor(private _route: ActivatedRoute,
              private _router: Router,
              private _threadService: ThreadService,
              private _categoryService: ForumCategoryService) { }

  ngOnInit() {
    this._route.params.subscribe(params => {
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
            this.updateHistory(params['id'] as string);
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

  public postAnswer() {
    const payload: ThreadAnswerApiRequest = {
      message: this.answer,
      creator: AuthenticatedUser.load().id,
    }
    this._threadService.createAnswer(this.thread.id, payload).subscribe(
      (status: boolean) => {
        location.reload();
      }
    );
  }

  private updateHistory(threadId: string) {
    const lastVisited: string[] = JSON.parse(localStorage.getItem('lastVisited') || '[]');
    if (!lastVisited.find(id => id === threadId)) {
      lastVisited.unshift(threadId);
    }
    lastVisited.splice(4, 1);
    localStorage.setItem('lastVisited', JSON.stringify(lastVisited));
  }
}
