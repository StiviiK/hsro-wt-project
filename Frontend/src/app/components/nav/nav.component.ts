import { Component, OnInit } from '@angular/core';
import { User, AuthenticatedUser } from '../../models/user/User';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {

  public title = 'HSRO-WT-Project';
  public user: User;

  constructor() { }

  ngOnInit() {
    this.user = AuthenticatedUser.load();
  }

}
