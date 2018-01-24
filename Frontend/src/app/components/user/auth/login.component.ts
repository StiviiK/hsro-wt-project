import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../../services/authentication/authentication';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  status: string;

  constructor(private _authService: AuthenticationService, private _router: Router) { }

  ngOnInit() {
  }

  login() {
    this._authService.login().subscribe(
      (result) => {
        this._router.navigate(['/!/dashboard']);
      },
      (err) => {
        console.error(err);
      }
    );
  }

  logout() {
    console.log(this._authService.logout());
  }
}
