import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Thread } from '../../../classes/forum/Thread';
import { ThreadService } from '../../../services/forum/thread.service';
import { ThreadAnswer } from '../../../classes/forum/ThreadAnswer';
import { User } from '../../../classes/user/User';

@Component({
  selector: 'app-forum-thread',
  templateUrl: './thread.component.html',
  styleUrls: ['./thread.component.scss']
})
export class ForumThreadComponent implements OnInit {

  thread: Thread;

  constructor(private _route: ActivatedRoute, private _router: Router, private _threadService: ThreadService) { }

  ngOnInit() {
    this._route.params.subscribe(params => {
      this._threadService.get(params['id'], true).subscribe(thread => {
        if (thread === undefined) {
          this._router.navigate(['error', '404']);
        } else {
          this.thread = thread;
        }
      });
    });
  }

}
