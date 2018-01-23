import { Component, OnInit } from '@angular/core';
import {AuthenticatedUser, User} from '../../models/user/User';
import {Thread} from '../../models/forum/Thread';
import {ThreadService} from '../../services/forum/thread';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {
  lastThreads: Thread[];
  user: User;

  constructor(private _threadService: ThreadService) { }

  ngOnInit() {
    this.user = AuthenticatedUser.load();
    this._threadService.getLastVisited().subscribe(threads => this.lastThreads = threads);
  }

}
