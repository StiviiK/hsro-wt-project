import { Component, OnInit } from '@angular/core';
import {ForumCategory} from '../../../models/forum/ForumCategory';
import {ActivatedRoute, Router} from '@angular/router';
import {ForumCategoryService} from '../../../services/forum/forum-category';
import { ThreadService } from '../../../services/forum/thread';
import { ThreadApiRequest } from '../../../models/interfaces/api/ApiRequest';
import { Thread } from '../../../models/forum/Thread';
import { AuthenticatedUser } from '../../../models/user/User';

@Component({
  selector: 'app-thread-create',
  templateUrl: './thread-create.component.html',
  styleUrls: ['./thread-create.component.css']
})
export class ThreadCreateComponent implements OnInit {
  public category: ForumCategory;
  public topic: string = "";
  public question: string = "";

  constructor(private _route: ActivatedRoute,
              private _router: Router,
              private _forumCategoryService: ForumCategoryService,
              private _threadService: ThreadService) { }

  ngOnInit() {
    this._route.params.subscribe(params => {
      this._forumCategoryService.get(params['categoryId']).subscribe(
        (category: ForumCategory) => {
          this.category = category;
        }
      );
    });
  }

  create() {
    const payload: ThreadApiRequest = {
      topic: this.topic,
      question: this.question,
      creator: AuthenticatedUser.load().id,
    }

    this._threadService.create(this.category.id, payload).subscribe(
      (id: number) => {
        this._router.navigate(['!', 'forum', this.category.id, 'thread', id]);
      }
    )
  }
}
