import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../../services/authentication/authentication';
import { Router } from '@angular/router';

@Component({
  template: ''
})
export class LogoutComponent implements OnInit {

  constructor(private _authService: AuthenticationService, private _router: Router) { }

  ngOnInit() {
    this._authService.logout();
    this._router.navigate(['/login']);
  }

}
