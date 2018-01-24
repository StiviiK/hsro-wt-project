import { Component, OnInit, Input } from '@angular/core';
import {ThreadAnswer} from '../../../models/forum/ThreadAnswer';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-thread-answer',
  templateUrl: './thread-answer.component.html',
  styleUrls: ['./thread-answer.component.scss']
})
export class ThreadAnswerComponent implements OnInit {
  @Input() answer: ThreadAnswer;
  @Input() count: Number;

  constructor(private _sanitizer: DomSanitizer) { }

  ngOnInit() {
  }

  public getAvatarImage() {
    return this._sanitizer.bypassSecurityTrustStyle(`url(${this.answer.creator.avatar})`);
  }
}
