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
    this._threadService.getHottest().subscribe(threads => this.hotThreads = threads);
    this._threadService.getLastVisited().subscribe(threads => this.lastThreads = threads);
  }
}
