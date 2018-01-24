import { Component, OnInit } from '@angular/core';
import { Thread } from '../../models/forum/Thread';
import { User, AuthenticatedUser } from '../../models/user/User';
import { ThreadService } from '../../services/forum/thread';

@Component({
  selector: 'app-enterpage',
  templateUrl: './enterpage.component.html',
  styleUrls: ['./enterpage.component.scss']
})
export class EnterpageComponent implements OnInit { // fill this with backend later
  hotThreads: Thread[] = [];
  lastThreads: Thread[] = [];
  constructor(private _threadService: ThreadService) { }

  ngOnInit() {
    // this._threadService.getHottest().subscribe(threads => this.hotThreads = threads);
    this._threadService.getLastVisited().subscribe(threads => this.lastThreads = threads);
  }
}
