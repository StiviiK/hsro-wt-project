import { Component, OnInit,Input } from '@angular/core';
import {ThreadAnswer} from '../../../models/forum/ThreadAnswer';

@Component({
  selector: 'app-thread-answer',
  templateUrl: './thread-answer.component.html',
  styleUrls: ['./thread-answer.component.css']
})
export class ThreadAnswerComponent implements OnInit {
@Input() answer: ThreadAnswer;
@Input() count: Number;
  constructor() { }

  ngOnInit() {
  }

}
