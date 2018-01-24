import { Component, OnInit } from '@angular/core';
import { AuthenticatedUser, User } from '../../models/user/User';
import { Thread } from '../../models/forum/Thread';
import { ThreadService } from '../../services/forum/thread';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../services/user/user';
import { ThreadAnswerJson } from '../../models/interfaces/api/JsonResponse';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {
  public user: User;
  public lastThreads: Thread[];
  public answeredThreads: Thread[];

  constructor(private _router: Router,
              private _route: ActivatedRoute,
              private _userService: UserService,
              private _threadService: ThreadService,
              private _sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.lastThreads = [];
    this.answeredThreads = [];

    this.user = AuthenticatedUser.load();
    this._route.params.subscribe(params => {
      this._userService.get(params['id']).subscribe(
        (user: User) => {
          this.user = user;
          this.user._topics.forEach(
            (id: number) => {
              this._threadService.get(id, 0).subscribe(
                (thread: Thread) => {
                  this.lastThreads.push(thread);
                }
              );
            }
          );

          this.user._answeredThreads.forEach(
            (id: number) => {
              this._threadService.get(id, 0).subscribe(
                (thread: Thread) => {
                  this.answeredThreads.push(thread);
                }
              );
            }
          );
        }
      );
    });
  }

  public getAvatarImage() {
    return this._sanitizer.bypassSecurityTrustStyle(`url(${this.user.avatar})`);
  }
}
