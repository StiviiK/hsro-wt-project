import { Component, OnInit } from '@angular/core';
import { Thread } from '../../classes/forum/Thread';
import { User } from '../../classes/user/User';
import { ThreadService } from '../../services/forum/thread.service';

@Component({
  selector: 'app-enterpage',
  templateUrl: './enterpage.component.html',
  styleUrls: ['./enterpage.component.scss']
})
export class EnterpageComponent implements OnInit { // fill this with backend later
  hotThreads: Thread[];
  lastThreads: Thread[];

  constructor(private _threadService: ThreadService) { }

  ngOnInit() {
    this.hotThreads = this._threadService.getHottest();
    this.lastThreads = this._threadService.getLastVisited();
  }

}
