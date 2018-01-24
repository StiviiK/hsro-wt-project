import { Component, OnInit, Input } from '@angular/core';
import {ThreadAnswer} from '../../../models/forum/ThreadAnswer';
import { DomSanitizer } from '@angular/platform-browser';
import { User, AuthenticatedUser } from '../../../models/user/User';
import { ThreadService } from '../../../services/forum/thread';

@Component({
  selector: 'app-thread-answer',
  templateUrl: './thread-answer.component.html',
  styleUrls: ['./thread-answer.component.scss']
})
export class ThreadAnswerComponent implements OnInit {
  @Input() answer: ThreadAnswer;
  @Input() count: Number;
  user: User;

  constructor(private _threadService: ThreadService,
              private _sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.user = AuthenticatedUser.load();
  }

  public getAvatarImage() {
    return this._sanitizer.bypassSecurityTrustStyle(`url(${this.answer.creator.avatar})`);
  }

  public removeAnswer() {
     this._threadService.removeAnswer(this.answer.thread, this.answer).subscribe(
        (status: boolean) => {
          location.reload();
        }
     );
  }
}
