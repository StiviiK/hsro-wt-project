import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Thread } from '../../../models/forum/Thread';
import { ThreadService } from '../../../services/forum/thread';
import { ThreadAnswer } from '../../../models/forum/ThreadAnswer';
import { User, AuthenticatedUser } from '../../../models/user/User';
import { ForumCategory } from '../../../models/forum/ForumCategory';
import { ForumCategoryService } from '../../../services/forum/forum-category';
import { ThreadAnswerApiRequest } from '../../../models/interfaces/api/ApiRequest';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-forum-thread',
  templateUrl: './thread.component.html',
  styleUrls: ['./thread.component.scss']
})
export class ForumThreadComponent implements OnInit {
  public thread: Thread;
  public answer: string;
  public image: string;
  public user: User;

  constructor(private _route: ActivatedRoute,
              private _router: Router,
              private _threadService: ThreadService,
              private _categoryService: ForumCategoryService,
              private _sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.user = AuthenticatedUser.load();
    this._route.params.subscribe(params => {
      this._threadService.get(params['id'], params['categoryId']).subscribe(
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
            this._threadService.increaseViews(thread).subscribe(
              (status: boolean) => {
                if (!status) {
                  console.error('Failed to increase view');
                }
              }
            );
          }
        }
      );
    });
  }

  public postAnswer() {
    const payload: ThreadAnswerApiRequest = {
      message: this.answer,
      creator: AuthenticatedUser.load().id,
    };

    this._threadService.createAnswer(this.thread, payload).subscribe(
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

  private removeFromHistory(threadId: string) {
    const lastVisited: string[] = JSON.parse(localStorage.getItem('lastVisited') || '[]');
    const index = lastVisited.findIndex(id => id === threadId);
    console.log(index);
    if (index > -1) {
      lastVisited.splice(index, 1);
    }
    localStorage.setItem('lastVisited', JSON.stringify(lastVisited));
  }

  public getAvatarImage() {
    return this._sanitizer.bypassSecurityTrustStyle(`url(${this.thread.creator.avatar})`);
  }

  public removeThread() {
    this._threadService.remove(this.thread).subscribe(
      (status: boolean) => {
        if (status) {
          this.removeFromHistory(String(this.thread.id));
          this._router.navigate(['!', 'forum', this.thread._category]);
        }
      }
    );
  }
}
